package com.huaxin.factory;

import com.huaxin.definition.BeanDefinition;
import com.huaxin.singleton.DefaultSingletonBeanRegister;

// bean工厂抽象类
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegister implements BeanFactory{
    @Override
    public Object getBean(String beanName) {
        Object bean = getSingleton(beanName);
        if(bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName,beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
