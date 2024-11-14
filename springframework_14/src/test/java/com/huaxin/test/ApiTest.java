package com.huaxin.test;

import com.huaxin.beans.bean.UserService;
import com.huaxin.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService bean = (UserService) context.getBean("userService");
        System.out.println(bean.queryUserInfo());
    }
}
