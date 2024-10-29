package com.huaxin.beans.singleton;

import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.lifecycle.DisposableBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    // 空单例对象标记: 用作并发Map(不支持空值)的标记值
    protected static final Object NULL_OBJECT = new Object();
    private Map<String,Object> singletonMap = new HashMap<>();
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public void addSingleton(String beanName, Object bean){
        singletonMap.put(beanName,bean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
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
