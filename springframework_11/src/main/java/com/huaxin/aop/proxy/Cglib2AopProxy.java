package com.huaxin.aop.proxy;

import com.huaxin.aop.advisedsupport.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Cglib2AopProxy implements AopProxy{
    private final AdvisedSupport advisedSupport;

    public Cglib2AopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setClassLoader(Thread.currentThread().getContextClassLoader());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor{
        private final AdvisedSupport advisedSupport;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,objects,methodProxy);
            if(advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTarget().getClass())){
                advisedSupport.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation{
        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target,this.arguments);
        }
    }
}
