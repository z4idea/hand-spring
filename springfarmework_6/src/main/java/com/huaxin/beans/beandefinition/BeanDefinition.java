package com.huaxin.beans.beandefinition;

public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    /**
     * 获取
     * @return beanClass
     */
    public Class getBeanClass() {
        return beanClass;
    }


    /**
     * 获取
     * @return propertyValues
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
