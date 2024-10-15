package com.huaxin.beans.factory;

import cn.hutool.core.bean.BeanUtil;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.BeanReference;
import com.huaxin.beans.beandefinition.PropertyValue;
import com.huaxin.beans.instanciationstrategy.CglibSubclassingInstantiationStrategy;
import com.huaxin.beans.instanciationstrategy.InstantiationStrategy;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    public Object getBean(String beanName) {
        return getBean(beanName, (Object[]) null);
    }

    @Override
    public Object getBean(String beanName, Object[] args) {
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        } else {
            Object bean = createBean(beanDefinition, args);
            addSingleton(beanName, bean);
            return bean;
        }
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType){
        return (T)getBean(beanName);
    }

    @Override
    protected Object createBean(BeanDefinition beanDefinition, Object[] args) {
        Object bean = createInstantiationBean(beanDefinition, args);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected Object createInstantiationBean(BeanDefinition beanDefinition, Object[] args) {
        Class beanClass = beanDefinition.getBeanClass();
        Constructor ctor = null;
        for (Constructor declaredConstructor : beanClass.getDeclaredConstructors()) {
            if (null != args && declaredConstructor.getParameterTypes().length == args.length) {
                boolean flag = true;
                for (int i = 0; i < beanClass.getTypeParameters().length; i++) {
                    if (beanClass.getTypeParameters()[i] != declaredConstructor.getTypeParameters()[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return instantiationStrategy.instantiate(args, declaredConstructor, beanDefinition);
                }
            }
        }
        return instantiationStrategy.instantiate(args, null, beanDefinition);
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        PropertyValue[] propertyValues = beanDefinition.getPropertyValues().getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                value = getBean(((BeanReference) value).getBeanName());
            }
            BeanUtil.setFieldValue(bean,name,value);
        }

    }

}
