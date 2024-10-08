package com.huaxin.beans.bean;

public class UserService {
    private String id;
    private UserDao userDao;

    public UserService(){}

    public void query(){
        System.out.println(userDao.queryInfo(this.id));
    }

}
