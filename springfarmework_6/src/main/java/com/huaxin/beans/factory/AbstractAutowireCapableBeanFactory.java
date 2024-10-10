package com.huaxin.beans.factory;

import cn.hutool.core.bean.BeanUtil;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.BeanReference;
import com.huaxin.beans.beandefinition.PropertyValue;
import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.instantiationstrategy.CglibSubclassingInstantiationStrategy;
import com.huaxin.beans.instantiationstrategy.InstantiationStrategy;
import com.huaxin.beans.postprocessor.BeanPostProcessor;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    @Override
    protected Object createBean(Object[] args, String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = doInstantiateBean(args, beanDefinition);
            addPropertyValues(bean,beanDefinition);
            bean = initializeBean(bean, beanDefinition);
        } catch (Exception e){
            throw new BeanException("实例化bean失败");
        }

        addSingletonBean(beanName,bean);
        return bean;
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

    protected void addPropertyValues(Object bean, BeanDefinition beanDefinition){
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                value = getBean(((BeanReference) value).getBeanName());
            }
            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    protected Object initializeBean(Object bean, BeanDefinition beanDefinition){
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

    private void invokeInitMethods(Object bean, BeanDefinition beanDefinition){

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
