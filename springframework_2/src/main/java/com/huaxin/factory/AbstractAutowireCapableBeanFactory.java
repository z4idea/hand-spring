package com.huaxin.factory;

import com.huaxin.definition.BeanDefinition;
import com.huaxin.exception.BeanException;

// 自动装配bean工厂抽象类
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException("Can`t createBean");
        }
        addSingleton(beanName,bean);
        return bean;
    }
}
