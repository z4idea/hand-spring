package com.huaxin.beans.beandefinitionreader;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.BeanDefinitionRegistry;
import com.huaxin.beans.beandefinition.BeanReference;
import com.huaxin.beans.beandefinition.PropertyValue;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.beandefinitionreader.scan.ClassPathBeanDefinitionScanner;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resourceloader.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try (InputStream is = resource.getInputStream()){
            doLoadBeanDefinitions(is);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanException(e.getMessage());
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        loadBeanDefinitions(getResourceLoader().getResource(location));
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            // 判断对象
            if("component-scan".equals(childNodes.item(i).getNodeName())){
                Element item = (Element) childNodes.item(i);
                String scanPath = item.getAttribute("base-package");
                if(StrUtil.isBlank(scanPath)){
                    throw new BeanException("包路径为空!!!");
                }
                scanPackage(scanPath);
            }
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }
            // 获取bean、id、name、className
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取 init-method、destroy-method
            String initMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");
            // 获取 scope
            String scope = bean.getAttribute("scope");
            // 获取clazz
            Class<?> clazz = Class.forName(className);
            // 获取beanName
            String beanName = StrUtil.isNotBlank(id) ? id :
                    StrUtil.isNotBlank(name) ? name :
                            StrUtil.lowerFirst(clazz.getSimpleName());
            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);
            if(StrUtil.isNotEmpty(scope)){
                beanDefinition.setScope(scope);
            }

            // 开始遍历bean的子节点
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] paths = StrUtil.splitToArray( scanPath,",");
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(getRegistry());
        classPathBeanDefinitionScanner.doScan(paths);
    }
}
