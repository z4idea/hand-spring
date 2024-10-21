package com.huaxin.beans.aware;

import com.huaxin.beans.factory.BeanFactory;

public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory);
}
