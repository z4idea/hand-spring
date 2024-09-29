package com.huaxin.instantiationstrategy;

import com.huaxin.beandefinition.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

// 基于Cglib的ASM指令码来实现实例化策略
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        // enhancer 增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // NoOp是一个没有实际操作（No - Operation）的回调类型.
        // 它通常用于在使用Enhancer创建代理类时,
        // 表示不需要对被代理方法进行任何特殊处理.
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(null == ctor){
            return enhancer.create();
        }else {
            return enhancer.create(ctor.getParameterTypes(),args);
        }
    }
}
