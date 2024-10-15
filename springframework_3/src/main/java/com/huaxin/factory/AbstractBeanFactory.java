package com.huaxin.factory;

import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.singleton.DefaultSingletonBean;

// bean工厂抽象类
public abstract class AbstractBeanFactory extends DefaultSingletonBean implements BeanFactory{
    @Override
    public Object getBean(Object[] args, String beanName) {
        Object bean = getSingletonBean(beanName);
        if(null != bean){
            return bean;
        }else {
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            return createBean(args,beanName,beanDefinition);
        }
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(Object[] args, String beanName, BeanDefinition beanDefinition);
}
