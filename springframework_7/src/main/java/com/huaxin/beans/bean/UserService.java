package com.huaxin.beans.bean;

import com.huaxin.beans.lifecycle.DisposableBean;
import com.huaxin.beans.lifecycle.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {
    private String id;
    private String company;
    private String location;
    private UserDao userDao;

    public UserService() {
    }

    public void query(){
        System.out.println("name:" + userDao.queryInfo(id) + ",company:" + company + ",location:" + location);
    }

    public void setLocation(String location){
        this.location = location;
    }

    @Override
    public void destroy() {
        System.out.println("执行: UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行: UserService.afterPropertiesSet");
    }
}
