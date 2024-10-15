package com.huaxin.beans.factory;

public interface AutowireCapableBeanFactory {
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean);
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean);
}
