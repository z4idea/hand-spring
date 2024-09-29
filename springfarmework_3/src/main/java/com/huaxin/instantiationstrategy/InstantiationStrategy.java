package com.huaxin.instantiationstrategy;

import com.huaxin.beandefinition.BeanDefinition;

import java.lang.reflect.Constructor;

// 实例化策略接口
public interface InstantiationStrategy {
    /**
     * Description: 实例化方法
     * @user 27166
     * @param beanDefinition 获取bean的class对象
     * @param beanName 使用beanName来完善输出报错信息
     * @param ctor 获取入参信息的构造函数
     * @param args 具体入参信息
     * @return 实例化对象
     **/

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
