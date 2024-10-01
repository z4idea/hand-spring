package com.huaxin.test;

import com.huaxin.bean.UserService;
import com.huaxin.definition.BeanDefinition;
import com.huaxin.factory.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

// 开始测试
public class ApiTest {

    @Test
    public void test(){
        // bean定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        // bean注册
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        // bean获取
        UserService userService = (UserService) beanFactory.getBean("userService");
        // bean再次获取(测试单例)
        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");

        userService.queryInfo();
        userServiceSingleton.queryInfo();
        System.out.println(userService == userServiceSingleton);
    }
}
