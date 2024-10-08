package com.huaxin.beans.singleton;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBean implements SingletonBean{
    private Map<String,Object> singletonMap = new HashMap<>();

    @Override
    public void addSingleton(String beanName, Object bean) {
        singletonMap.put(beanName,bean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }
}
