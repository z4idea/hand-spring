package com.huaxin.beans.factory;

import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.postprocessor.BeanPostProcessor;
import com.huaxin.beans.singleton.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory{
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        // 先删除后添加 防止重复添加
        beanPostProcessors.remove(postProcessor);
        beanPostProcessors.add(postProcessor);
    }

    @Override
    public Object getBean(Object[] args, String beanName) {
        return doGetBean(args,beanName);
    }

    @Override
    public Object getBean(String beanName) {
        return getBean(null,beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return (T)getBean(beanName);
    }

    protected <T> T doGetBean(Object[] args, String beanName){
        Object singletonBean = getSingleton(beanName);
        if(singletonBean != null){
            return (T)singletonBean;
        } else {
            return (T)createBean(args,beanName,getBeanDefinition(beanName));
        }
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(Object[] args, String beanName, BeanDefinition beanDefinition);
}
