package com.huaxin.factory;

import cn.hutool.core.bean.BeanUtil;
import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.beandefinition.BeanReference;
import com.huaxin.beandefinition.PropertyValue;
import com.huaxin.beandefinition.PropertyValues;
import com.huaxin.instantiationstrategy.CglibSubclassingInstantiationStrategy;
import com.huaxin.instantiationstrategy.InstantiationStrategy;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    public Object getBean(String beanName, Object[] args) {
        Object singletonBean = getSingletonBean(beanName);
        if (singletonBean != null) {
            return singletonBean;
        } else {
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            return createBean(beanName, beanDefinition, args);
        }

    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = createInstantiationBean(beanDefinition, args);
        applyPropertyValues(bean, beanDefinition);
        addSingletonBean(beanName, bean);
        return bean;
    }

    protected Object createInstantiationBean(BeanDefinition beanDefinition, Object[] args) {
        Class beanClass = beanDefinition.getBeanClass();
        Constructor ctor = null;
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            if (null != args && declaredConstructor.getParameterTypes().length == args.length) {
                boolean flag = true;
                for (int i = 0; i < args.length; i++) {
                    if (args[i].getClass() != declaredConstructor.getParameterTypes()[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ctor = declaredConstructor;
                }
            }
        }
        return instantiationStrategy.instantiate(args, ctor, beanDefinition);
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String propertyName = propertyValue.getPropertyName();
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                value = getBean(((BeanReference) value).getBeanName(), null);
            }
            BeanUtil.setFieldValue(bean,propertyName,value);
        }
    }
}
