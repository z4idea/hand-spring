package com.huaxin.beans.postprocessor;

import com.huaxin.beans.exception.BeanException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;
}
