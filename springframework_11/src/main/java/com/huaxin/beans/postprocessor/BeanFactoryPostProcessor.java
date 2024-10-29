package com.huaxin.beans.postprocessor;

import com.huaxin.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory factory);
}
