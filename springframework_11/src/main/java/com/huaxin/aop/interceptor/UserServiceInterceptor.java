package com.huaxin.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("监控 start By AOP");
            System.out.println("方法名称:" + methodInvocation.getMethod().getName());
            System.out.println("方法耗时:" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 end By AOP");
        }
    }
}
