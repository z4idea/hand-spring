package com.huaxin.beans.singleton;

public interface SingletonBean {
    void addSingleton(String beanName,Object bean);
    Object getSingleton(String beanName);
}
