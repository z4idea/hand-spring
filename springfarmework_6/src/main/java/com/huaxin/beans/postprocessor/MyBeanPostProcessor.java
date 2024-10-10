package com.huaxin.beans.postprocessor;

import com.huaxin.beans.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean) {
        if (bean.getClass() == UserService.class) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitiation(Object bean) {
        return bean;
    }
}
