package com.huaxin.beans.factorybean;

import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.singleton.DefaultSingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = factoryBeanObjectCache.get(beanName);
        return object != NULL_OBJECT ? object : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName){
        if(factory.isSingleton()){
            Object object = getCachedObjectForFactoryBean(beanName);
            if(null == object){
                object = doGetObjectFromFactoryBean(factory,beanName);
                factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return object != NULL_OBJECT ? object : null;
        } else {
            return doGetObjectFromFactoryBean(factory,beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeanException("FactoryBean 无法创建" + beanName + " bean");
        }
    }
}
