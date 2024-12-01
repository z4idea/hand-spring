package com.huaxin.context;

import com.huaxin.beans.factory.ConfigurableListableBeanFactory;
import com.huaxin.beans.postprocessor.bean.ApplicationContextAwareProcessor;
import com.huaxin.beans.postprocessor.bean.BeanPostProcessor;
import com.huaxin.beans.postprocessor.beanfactory.BeanFactoryPostProcessor;
import com.huaxin.context.event.ApplicationEvent;
import com.huaxin.context.event.ContextClosedEvent;
import com.huaxin.context.event.ContextRefreshedEvent;
import com.huaxin.context.event.listener.ApplicationListener;
import com.huaxin.context.event.multicast.ApplicationEventMulticaster;
import com.huaxin.context.event.multicast.SimpleApplicationEventMulticaster;
import com.huaxin.core.convert.service.ConversionService;
import com.huaxin.core.io.resourceloader.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() {
        // 1.创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        // 2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3.注册ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 4.在Bean实例化之前,执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 5.BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessor(beanFactory);
        // 6.初始化事件发布者
        initApplicationEventMulticaster();
        // 7.注册事件监听器
        registerListeners();
        // 8.提前实例化单例化Bean对象
        finishBeanFactoryInitialization(beanFactory);
        // 9.发布容器并刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory factory) {
        Map<String, BeanFactoryPostProcessor> beansOfType = factory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor value : beansOfType.values()) {
            value.postProcessBeanFactory(factory);
        }
    }

    protected void registerBeanPostProcessor(ConfigurableListableBeanFactory factory) {
        Map<String, BeanPostProcessor> beansOfType = factory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor value : beansOfType.values()) {
            factory.addBeanPostProcessor(value);
        }
    }

    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    protected void registerListeners() {
        Map<String, ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class);
        for (ApplicationListener listener : applicationListeners.values()) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    // 设置类型转换器、提前实例化单例Bean对象
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // 设置类型转换器
        Object conversionService = beanFactory.getBean("conversionService");
        if(conversionService != null){
            if (conversionService instanceof ConversionService) {
                beanFactory.setConversionService((ConversionService) conversionService);
            }
        }
        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(Object[] args, String beanName) {
        return getBeanFactory().getBean(args, beanName);
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return getBeanFactory().getBean(beanName, requiredType);
    }
}
