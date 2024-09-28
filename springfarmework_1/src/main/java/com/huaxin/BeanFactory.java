package com.huaxin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Bean的注册和获取
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void registerBean(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }

    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

}
