package com.huaxin.aop.advice;

import java.lang.reflect.Method;

public class UserServiceBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截Method:" + method.getName());
    }
}
