package com.huaxin.beandefinition;

public class PropertyValue {
    private String propertyName;
    private Object value;

    public PropertyValue() {
    }

    public PropertyValue(String propertyName, Object value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    /**
     * 获取
     * @return name
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.propertyName = propertyName;
    }

    /**
     * 获取
     * @return value
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public String toString() {
        return "PropertyValue{propertyName = " + propertyName + ", value = " + value + "}";
    }
}
