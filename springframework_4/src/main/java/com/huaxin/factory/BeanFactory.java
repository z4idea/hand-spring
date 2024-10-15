package com.huaxin.factory;

public interface BeanFactory {
    Object getBean(String beanName, Object[] args);
}
