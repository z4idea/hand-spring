package com.huaxin.beans.factory;

public interface BeanFactory {
    Object getBean(Object[] args, String beanName);
    Object getBean(String beanName);
    <T> T getBean(String beanName, Class<T> requiredType);
}
