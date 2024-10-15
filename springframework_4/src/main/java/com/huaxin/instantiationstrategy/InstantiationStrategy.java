package com.huaxin.instantiationstrategy;

import com.huaxin.beandefinition.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(Object[] args, Constructor ctor, BeanDefinition beanDefinition);
}
