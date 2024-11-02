package com.huaxin.beans.singleton;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    void addSingleton(String beanName, Object bean);
}
