package com.huaxin.singleton;

import java.util.HashMap;
import java.util.Map;

// 单例模式bean获取的实现类
public class DefaultSingletonBean implements SingletonBean{
    private Map<String,Object> singletonBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    public void registerSingletonBean(String beanName, Object singletonBean){
        singletonBeanMap.put(beanName,singletonBean);
    }
}
