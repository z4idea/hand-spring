package com.huaxin.factory;

// bean工厂
public interface BeanFactory {
    // 获得具体bean
    Object getBean(String beanName);
}
