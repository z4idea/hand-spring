package com.huaxin.beans.instanciationstrategy;

import com.huaxin.beans.beandefinition.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(Object[] args, Constructor ctor, BeanDefinition beanDefinition) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(ctor == null){
            return enhancer.create();
        } else {
            return enhancer.create(ctor.getParameterTypes(),args);
        }
    }
}
