package com.huaxin.beans.factory;

import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.factorybean.FactoryBean;
import com.huaxin.beans.factorybean.FactoryBeanRegistrySupport;
import com.huaxin.beans.postprocessor.bean.BeanPostProcessor;
import com.huaxin.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory{
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    public ClassLoader getBeanClassLoader(){
        return beanClassLoader;
    }

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

    protected <T> T doGetBean(final Object[] args, final String beanName){
        Object singletonBean = getSingleton(beanName);
        if(singletonBean != null){
            return (T) getObjectForBeanInstance(singletonBean,beanName);
        } else {
            Object bean = createBean(args, beanName, getBeanDefinition(beanName));
            return (T) getObjectForBeanInstance(bean,beanName);
        }
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(Object[] args, String beanName, BeanDefinition beanDefinition);

    private Object getObjectForBeanInstance(Object beanInstance, String beanName){
        if(!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if(null == object){
            object = getObjectFromFactoryBean((FactoryBean) beanInstance,beanName);
        }
        return object;
    }
}
