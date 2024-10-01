package com.huaixn.test;

import com.huaxin.BeanDefinition;
import com.huaxin.BeanFactory;
import com.huaxin.bean.UserService;
import org.junit.jupiter.api.Test;

// 测试
public class ApiTest {

    @Test
    public void test() {
        // 定义Bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        // 注册Bean
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("userService",beanDefinition);
        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryInfo();
    }


}
