package com.huaxin.beans.instanciationstrategy;

import com.huaxin.beans.beandefinition.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(Object[] args, Constructor ctor, BeanDefinition beanDefinition);
}
