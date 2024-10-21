package com.huaxin.beans.singleton;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    void destroySingletons();
}
