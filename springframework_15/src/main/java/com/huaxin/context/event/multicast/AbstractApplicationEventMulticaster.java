package com.huaxin.context.event.multicast;

import com.huaxin.beans.aware.BeanFactoryAware;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.factory.BeanFactory;
import com.huaxin.context.event.ApplicationEvent;
import com.huaxin.context.event.listener.ApplicationListener;
import com.huaxin.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    private BeanFactory beanFactory;

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if(supportsEvent(listener,event)){
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event){
        Class<? extends ApplicationListener> clazz = listener.getClass();
        Class<?> targetClass = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        // 获取实现类 实现的第一个接口 即 ApplicationListener<?>
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        // 获取泛型参数 即 ?
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeanException("无法获取事件class:" + className + ",原因:" + e.getMessage());
        }
        // 判断子类和父类关系或者接口和实现类关系
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
