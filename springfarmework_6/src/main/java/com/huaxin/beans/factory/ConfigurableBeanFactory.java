package com.huaxin.beans.factory;

import com.huaxin.beans.postprocessor.BeanPostProcessor;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE ="prototype";

    void addBeanPostProcessor(BeanPostProcessor postProcessor);

}
