package com.huaxin.context;

import com.huaxin.beans.factory.ConfigurableListableBeanFactory;
import com.huaxin.beans.postprocessor.BeanFactoryPostProcessor;
import com.huaxin.beans.postprocessor.BeanPostProcessor;
import com.huaxin.core.io.resourceloader.DefaultResourceLoader;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{
    @Override
    public void refresh() {
        // 1.创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        // 2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3.在Bean实例化之前,执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4.BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessor(beanFactory);
        // 5.提前实例化单例化Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory factory){
        Map<String, BeanFactoryPostProcessor> beansOfType = factory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor value : beansOfType.values()) {
            value.postProcessBeanFactory(factory);
        }
    }

    protected void registerBeanPostProcessor(ConfigurableListableBeanFactory factory){
        Map<String, BeanPostProcessor> beansOfType = factory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor value : beansOfType.values()) {
            factory.addBeanPostProcessor(value);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return Collections.emptyMap();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(Object[] args, String beanName) {
        return getBeanFactory().getBean(args,beanName);
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return getBeanFactory().getBean(beanName,requiredType);
    }
}
