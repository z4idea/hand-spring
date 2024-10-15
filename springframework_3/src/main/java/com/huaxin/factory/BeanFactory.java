package com.huaxin.factory;

// bean工厂接口
public interface BeanFactory {
    Object getBean(Object[] args, String beanName);
}
