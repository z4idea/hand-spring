package com.huaxin.beans.factory;

import com.huaxin.beans.beandefinition.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory,ConfigurableBeanFactory,AutowireCapableBeanFactory{
    BeanDefinition getBeanDefinition(String beanName);
}
