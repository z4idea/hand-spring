package com.huaxin.beans.factory;

import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.singleton.DefaultSingletonBean;

public abstract class AbstractBeanFactory extends DefaultSingletonBean implements BeanFactory{
    public abstract Object getBean(String beanName);

    public abstract Object getBean(String beanName, Object[] args);

    public abstract <T> T getBean(String beanName,Class<T> requiredType);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(BeanDefinition beanDefinition, Object[] args);
}
