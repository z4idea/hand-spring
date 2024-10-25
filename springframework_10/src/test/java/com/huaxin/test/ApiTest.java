package com.huaxin.test;

import com.huaxin.context.ClassPathXmlApplicationContext;
import com.huaxin.test.event.CustomEvent;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.publishEvent(new CustomEvent(context,"100001","zhjj"));
        context.registerShutdownHook();

    }
}
