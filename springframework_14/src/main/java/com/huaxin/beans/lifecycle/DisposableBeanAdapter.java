package com.huaxin.beans.lifecycle;

import cn.hutool.core.util.StrUtil;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.exception.BeanException;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean{
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method method = bean.getClass().getMethod(destroyMethodName);
            if(null == method){
                throw new BeanException("无法找到" + destroyMethodName + "方法");
            }
            method.invoke(bean);
        }
    }
}
