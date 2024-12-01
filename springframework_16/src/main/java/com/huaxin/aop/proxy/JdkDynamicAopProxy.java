package com.huaxin.aop.proxy;

import com.huaxin.aop.advisedsupport.AdvisedSupport;
import com.huaxin.aop.invocation.ReflectiveMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTarget().getClass())){
            advisedSupport.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
    }
}
