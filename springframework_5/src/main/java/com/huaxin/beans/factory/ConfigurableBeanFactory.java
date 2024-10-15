package com.huaxin.beans.factory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

}
