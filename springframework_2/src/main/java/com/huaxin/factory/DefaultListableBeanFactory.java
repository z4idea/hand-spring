package com.huaxin.factory;

import com.huaxin.definition.BeanDefinition;
import com.huaxin.definition.BeanDefinitionRegister;

import java.util.HashMap;
import java.util.Map;

// 最终bean工厂实现类
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegister {
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }
}
