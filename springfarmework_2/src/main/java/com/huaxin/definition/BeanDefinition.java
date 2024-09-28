package com.huaxin.definition;

// bean的定义
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass(){
        return this.beanClass;
    }
}
