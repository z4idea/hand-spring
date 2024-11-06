package com.huaxin.test;

import com.huaxin.beans.bean.IUserService;
import com.huaxin.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test2Placeholder(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println("userService = " + userService);
    }
    
    @Test
    public void test2BasePackage(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
