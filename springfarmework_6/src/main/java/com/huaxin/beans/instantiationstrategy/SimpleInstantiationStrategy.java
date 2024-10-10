package com.huaxin.beans.instantiationstrategy;

import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.exception.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(Object[] args, Constructor ctor, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if(ctor == null){
                return beanClass.newInstance();
            } else {
                return ctor.newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanException("bean的实例化失败,原因:" + e.getMessage());
        }
    }
}
