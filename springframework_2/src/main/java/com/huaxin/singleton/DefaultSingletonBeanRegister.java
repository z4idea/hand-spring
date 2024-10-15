package com.huaxin.singleton;

import java.util.HashMap;
import java.util.Map;

// 单例bean获取的实现类
public class DefaultSingletonBeanRegister implements SingletonBeanRegister{
    private Map<String,Object> singletonMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    public void addSingleton(String beanName, Object singletonBean){
        singletonMap.put(beanName,singletonBean);
    }
}
