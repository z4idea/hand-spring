package com.huaxin.beans.singleton;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBean implements SingletonBean{
    private Map<String,Object> singletonMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonMap.get(beanName);
    }

    public void addSingletonBean(String beanName, Object bean){
        singletonMap.put(beanName,bean);
    }
}
