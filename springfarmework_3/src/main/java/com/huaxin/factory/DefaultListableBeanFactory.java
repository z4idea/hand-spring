package com.huaxin.factory;

import com.huaxin.beandefinition.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory{
    private Map<String,BeanDefinition> beanDefinitionMap =  new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
