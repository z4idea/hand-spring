package com.huaxin.test;

import com.huaxin.bean.UserService;
import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.factory.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {

    // 普通实例化方法测试
    @Test
    public void test_noArgs() throws InstantiationException, IllegalAccessException {
        UserService userService = new UserService(111,"z4idea");
        BeanDefinition beanDefinition = new BeanDefinition(userService.getClass());
        UserService bean = (UserService)beanDefinition.getBeanClass().newInstance();
        bean.queryInfo();

        // 结果抛出异常
    }

    // 有参实例化方法测试
    @Test
    public void test_Args(){
        // bean的定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        // bean的注册
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition);
        // bean的获取
        UserService bean = (UserService) defaultListableBeanFactory.getBean(new Object[]{111,"z4idea"}, "userService");
        bean.queryInfo();
        // 单例模式bean获取
        UserService singletonBean = (UserService) defaultListableBeanFactory.getBean(new Object[]{111,"z4idea"}, "userService");
        singletonBean.queryInfo();
    }
}
