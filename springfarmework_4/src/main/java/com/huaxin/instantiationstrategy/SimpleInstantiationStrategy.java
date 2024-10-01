package com.huaxin.instantiationstrategy;

import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.exception.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(Object[] args, Constructor ctor, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            if(null == ctor){
                return beanClass.newInstance();
            }
            bean = ctor.newInstance(args);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new BeanException("bean无法实例化,错误信息:" + e.getMessage());
        }
        return bean;
    }
}
