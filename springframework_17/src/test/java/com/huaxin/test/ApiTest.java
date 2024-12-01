package com.huaxin.test;

import com.huaxin.context.ClassPathXmlApplicationContext;
import com.huaxin.test.bean.Husband;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果：" + husband);
    }
}
