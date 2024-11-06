package com.huaxin.beans.postprocessor.bean;

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean);

    Object postProcessAfterInitiation(Object bean);
}
