package com.huaxin.aop.proxyfactory;

import com.huaxin.aop.advisedsupport.AdvisedSupport;
import com.huaxin.aop.proxy.AopProxy;
import com.huaxin.aop.proxy.Cglib2AopProxy;
import com.huaxin.aop.proxy.JdkDynamicAopProxy;

public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
