package com.huaxin.beans.postprocessor.beanfactory;

import com.huaxin.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory factory);
}
