package com.huaxin.beans.postprocessor.beanfactory;

import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.PropertyValue;
import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.factory.ConfigurableListableBeanFactory;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resourceloader.DefaultResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    private static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(location);
        Properties properties = new Properties();
        try {
            properties.load(resource.getInputStream());
        } catch (IOException e) {
            throw new BeanException("无法解析 " + location + " 位置的文件");
        }
        String[] beanDefinitionNames = factory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanDefinitionName);
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                Object value = propertyValue.getValue();
                if (!(value instanceof String)) {
                    continue;
                }
                String strValue = (String) value;
                StringBuilder builder = new StringBuilder(strValue);
                int startIndex = strValue.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                int lastIndex = strValue.lastIndexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                if (startIndex != -1 && lastIndex != -1 && startIndex < lastIndex) {
                    String strKey = strValue.substring(startIndex + 2, lastIndex);
                    builder.replace(startIndex, lastIndex + 1, properties.getProperty(strKey));
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), builder.toString()));
                }
            }
        }
    }
}
