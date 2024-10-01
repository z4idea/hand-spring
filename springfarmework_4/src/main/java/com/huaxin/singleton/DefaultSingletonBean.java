package com.huaxin.singleton;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBean implements SingletonBean{
    private Map<String,Object> singletonBeanMap = new HashMap<>();


    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    public void addSingletonBean(String beanName, Object bean){
        singletonBeanMap.put(beanName,bean);
    }
}
