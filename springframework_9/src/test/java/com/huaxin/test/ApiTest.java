package com.huaxin.test;

import com.huaxin.beans.bean.UserService;
import com.huaxin.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_prototype(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService bean1 = (UserService)applicationContext.getBean("userService");
        UserService bean2 = (UserService)applicationContext.getBean("userService");
        bean1.query();
        System.out.println(bean1 == bean2);
    }

    @Test
    public void test_singleton(){
        // 记得修改配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService bean1 = (UserService)applicationContext.getBean("userService");
        UserService bean2 = (UserService)applicationContext.getBean("userService");
        bean1.query();
        System.out.println(bean1 == bean2);
    }
}
