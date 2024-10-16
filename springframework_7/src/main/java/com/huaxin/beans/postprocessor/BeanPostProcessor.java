package com.huaxin.beans.postprocessor;

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean);

    Object postProcessAfterInitiation(Object bean);
}
