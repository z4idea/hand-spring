package com.huaxin.definition;

// Bean的注册接口
public interface BeanDefinitionRegister {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
