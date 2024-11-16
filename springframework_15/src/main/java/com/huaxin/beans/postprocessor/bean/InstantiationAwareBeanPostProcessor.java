package com.huaxin.beans.postprocessor.bean;

import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.exception.BeanException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName);

    boolean postProcessAfterInstantiation(Object bean, String beanName);
}
