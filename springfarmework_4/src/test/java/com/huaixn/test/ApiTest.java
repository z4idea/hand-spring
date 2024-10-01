package com.huaixn.test;

import com.huaxin.bean.UserDao;
import com.huaxin.bean.UserService;
import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.beandefinition.BeanReference;
import com.huaxin.beandefinition.PropertyValue;
import com.huaxin.beandefinition.PropertyValues;
import com.huaxin.factory.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test(){
        // bean的定义
        BeanDefinition beanDefinition1 = new BeanDefinition(UserDao.class);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id","02"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinition2 = new BeanDefinition(UserService.class,propertyValues);
        // bean的注册
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("userDao",beanDefinition1);
        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition2);
        // bean的获取
        UserService bean = (UserService)defaultListableBeanFactory.getBean("userService", new Object[]{"华信软件学院"});
        UserService singletonBean = (UserService)defaultListableBeanFactory.getBean("userService", new Object[]{"华信软件学院"});
        // 测试
        bean.queryUserinfo();
        singletonBean.queryUserinfo();
        System.out.println(bean == singletonBean ? "单例测试成功" : "单例测试失败");

    }

}
