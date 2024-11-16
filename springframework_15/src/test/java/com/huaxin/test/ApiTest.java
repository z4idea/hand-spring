package com.huaxin.test;

import com.huaxin.test.bean.IUserService;
import com.huaxin.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
