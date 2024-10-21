package com.huaxin.beans.factory;

import com.huaxin.beans.postprocessor.BeanPostProcessor;
import com.huaxin.beans.singleton.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE ="prototype";

    void addBeanPostProcessor(BeanPostProcessor postProcessor);

}
