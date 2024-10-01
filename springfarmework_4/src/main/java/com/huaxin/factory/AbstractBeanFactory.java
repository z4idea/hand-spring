package com.huaxin.factory;

import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.singleton.DefaultSingletonBean;

public abstract class AbstractBeanFactory extends DefaultSingletonBean implements BeanFactory{
    public abstract Object getBean(String beanName, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
}
