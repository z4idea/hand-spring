package com.huaxin.beans.factory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.huaxin.beans.aware.Aware;
import com.huaxin.beans.aware.BeanClassLoaderAware;
import com.huaxin.beans.aware.BeanFactoryAware;
import com.huaxin.beans.aware.BeanNameAware;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.BeanReference;
import com.huaxin.beans.beandefinition.PropertyValue;
import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.instantiationstrategy.CglibSubclassingInstantiationStrategy;
import com.huaxin.beans.instantiationstrategy.InstantiationStrategy;
import com.huaxin.beans.lifecycle.DisposableBean;
import com.huaxin.beans.lifecycle.DisposableBeanAdapter;
import com.huaxin.beans.lifecycle.InitializingBean;
import com.huaxin.beans.postprocessor.BeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    @Override
    protected Object createBean(Object[] args, String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = doInstantiateBean(args, beanDefinition);
            addPropertyValues(bean,beanDefinition,beanName);
            bean = initializeBean(bean, beanDefinition,beanName);
        } catch (Exception e){
            throw new BeanException("实例化bean失败:" + e.getMessage());
        }

        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        // 判断scope是 singleton / prototype
        if(beanDefinition.isSingleton()){
            addSingleton(beanName,bean);
        }
        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(!beanDefinition.isSingleton()){
            return;
        }
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }

    protected Object doInstantiateBean(Object[] args, BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Constructor ctor = null;
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            if(null != args && declaredConstructor.getTypeParameters().length == args.length){
                boolean flag = true;
                for (int i = 0; i < declaredConstructor.getParameterTypes().length; i++) {
                    if(!declaredConstructor.getParameterTypes()[i].equals(args[i].getClass())){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    ctor = declaredConstructor;
                }
            }
        }
        return instantiationStrategy.instantiate(args,ctor,beanDefinition);
    }

    protected void addPropertyValues(Object bean, BeanDefinition beanDefinition, String beanName){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference){
                    value = getBean(((BeanReference) value).getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e){
            throw new BeanException("Error setting property values:" + beanName);
        }

    }

    protected Object initializeBean(Object bean, BeanDefinition beanDefinition,String beanName) throws Exception {
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
        }

        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean);
        invokeInitMethods(wrappedBean, beanDefinition);
        wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    private void invokeInitMethods(Object bean, BeanDefinition beanDefinition) throws Exception {
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null == method){
                throw new BeanException("无法找到" + initMethodName + "方法");
            }
            method.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result);
            if(null == current) return result;
            result = current;
        }
        return result;
    }
}
