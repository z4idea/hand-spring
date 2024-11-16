package com.huaxin.beans.postprocessor.bean;

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean);

    Object postProcessAfterInitialization(Object bean);
}
