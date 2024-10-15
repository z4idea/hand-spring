package com.huaxin.instantiationstrategy;

import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.exception.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 基于Java自带的DeclaredConstructor实现实例化策略
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            if(null == ctor){
                return beanClass.newInstance();
            }else {
                Constructor declaredConstructor = beanClass.getConstructor(ctor.getParameterTypes());
                bean = declaredConstructor.newInstance(args);
            }
        } catch (NoSuchMethodException | InvocationTargetException
                | InstantiationException | IllegalAccessException e) {
            throw new BeanException(beanName + " can`t  be created! Because " + e.getMessage());
        }
        return bean;
    }
}
