package com.huaxin.test;

import com.huaxin.beans.bean.UserService;
import com.huaxin.beans.beandefinitionreader.XmlBeanDefinitionReader;
import com.huaxin.beans.factory.DefaultListableBeanFactory;
import com.huaxin.beans.postprocessor.MyBeanFactoryPostProcessor;
import com.huaxin.beans.postprocessor.MyBeanPostProcessor;
import com.huaxin.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_normal(){
        // 1.初始化BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 2.读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3.BeanDefinition加载完成 & Bean实例化之前,修改BeanDefinition属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(factory);
        // 4.Bean实例化之后,修改Bean的属性值
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        factory.addBeanPostProcessor(beanPostProcessor);
        // 5.获取Bean对象调用方法
        UserService userService = factory.getBean("userService", UserService.class);
        userService.query();
    }

    @Test
    public void test_xml(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        // 2.获取Bean对象调用方法
        UserService bean = (UserService)context.getBean("userService");
        bean.query();
    }
}
