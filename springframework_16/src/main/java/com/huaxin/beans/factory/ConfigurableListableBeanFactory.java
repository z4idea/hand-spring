package com.huaxin.beans.factory;

import com.huaxin.beans.beandefinition.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory,ListableBeanFactory,AutowireCapableBeanFactory{
    BeanDefinition getBeanDefinition(String beanName);

    void preInstantiateSingletons();
}
