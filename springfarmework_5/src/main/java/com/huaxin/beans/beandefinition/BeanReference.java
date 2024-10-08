package com.huaxin.beans.beandefinition;

public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取
     * @return beanName
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置
     * @param beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String toString() {
        return "BeanReference{beanName = " + beanName + "}";
    }
}
