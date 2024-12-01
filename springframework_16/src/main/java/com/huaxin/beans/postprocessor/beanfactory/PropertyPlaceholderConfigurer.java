package com.huaxin.beans.postprocessor.beanfactory;

import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.PropertyValue;
import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.factory.ConfigurableListableBeanFactory;
import com.huaxin.beans.factory.resolver.StringValueResolver;
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
                value = resolvePlaceholder((String) value,properties);
                propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
            }
        }

        StringValueResolver resolver = new PlaceholderResolvingStringValueResolver(properties);
        factory.addEmbeddedValueResolver(resolver);
    }
    
    private String resolvePlaceholder(String value, Properties properties){
        String strValue = value;
        StringBuilder builder = new StringBuilder(strValue);
        // 先判断是否存在占位符
        int startIndex = strValue.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int lastIndex = strValue.lastIndexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        // 开始替换
        if (startIndex != -1 && lastIndex != -1 && startIndex < lastIndex) {
            String strKey = strValue.substring(startIndex + 2, lastIndex);
            builder.replace(startIndex, lastIndex + 1, properties.getProperty(strKey));
        }
        return builder.toString();
    }
    
    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolvesStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal,properties);
        }
    }
}
