package com.huaxin.beans.singleton;

import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.factory.design.ObjectFactory;
import com.huaxin.beans.lifecycle.DisposableBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    // 空单例对象标记: 用作并发Map(不支持空值)的标记值
    protected static final Object NULL_OBJECT = new Object();
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    // 一级缓存
    private final Map<String,Object> singletonObjects = new ConcurrentHashMap<>();
    // 二级缓存
    private final Map<String,Object> earlySingletonObjects = new ConcurrentHashMap<>();
    // 三级缓存
    private final Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();
    

    @Override
    public void addSingleton(String beanName, Object bean){
        singletonObjects.put(beanName,bean);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    @Override
    public Object getSingleton(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if(null == bean) {
            bean = earlySingletonObjects.get(beanName);
            if(null == bean) {
                ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
                if(objectFactory != null){
                    bean = objectFactory.getObject();
                    earlySingletonObjects.put(beanName,bean);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return bean;
    }
    
    public void addSingletonFactory(String beanName, ObjectFactory<?> objectFactory){
        if(!singletonObjects.containsKey(beanName)){
            singletonFactories.put(beanName,objectFactory);
            earlySingletonObjects.remove(beanName);
        }
    }
    
    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }

    public void destroySingletons() {
        Set<String> keySet = disposableBeans.keySet();
        String[] beanNames = keySet.toArray(new String[0]);

        for (int i = beanNames.length - 1; i >= 0; i--) {
            DisposableBean disposableBean = disposableBeans.remove(beanNames[i]);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException("无法执行bean的删除过程");
            }
        }
    }
}
