package com.huaxin.beans.postprocessor.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.huaxin.beans.aware.BeanFactoryAware;
import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.beandefinitionreader.annotation.Autowired;
import com.huaxin.beans.beandefinitionreader.annotation.Qualifier;
import com.huaxin.beans.beandefinitionreader.annotation.Value;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.factory.BeanFactory;
import com.huaxin.beans.factory.ConfigurableListableBeanFactory;
import com.huaxin.util.ClassUtils;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) {
        Class<?> clazz = bean.getClass();
        // 判断是否是 cglib 代理的类
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        // 处理 @Value
        for (Field field : clazz.getDeclaredFields()) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null == valueAnnotation) {
                continue;
            }
            String value = valueAnnotation.value();
            value = beanFactory.resolveEmbeddedValue(value);
            BeanUtil.setFieldValue(bean, field.getName(), value);
        }
        // 处理 @Autowired
        for (Field field : clazz.getDeclaredFields()) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null == autowiredAnnotation) {
                continue;
            }
            Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
            if (null != qualifierAnnotation) {
                String name = qualifierAnnotation.value();
                Object value = beanFactory.getBean(name);
                BeanUtil.setFieldValue(bean, name, value);
            } else {
                String name = StrUtil.lowerFirst(field.getType().getSimpleName());
                Object value = beanFactory.getBean(name);
                BeanUtil.setFieldValue(bean, name, value);
            }
        }
        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }


}
