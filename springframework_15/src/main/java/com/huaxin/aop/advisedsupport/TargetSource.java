package com.huaxin.aop.advisedsupport;

import com.huaxin.util.ClassUtils;

public class TargetSource {
    private final Object target;

    public TargetSource(Object target){
        this.target = target;
    }

    public Object getTarget(){
        return this.target;
    }

    public Class<?>[] getTargetClass(){
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }
}
