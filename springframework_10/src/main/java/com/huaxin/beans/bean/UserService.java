package com.huaxin.beans.bean;

public class UserService {
    private String id;
    private String company;
    private String location;
    private IUserDao userDao;

    public UserService() {
    }

    public void query(){
        System.out.println("name:" + userDao.queryUserName(id) + ",company:" + company + ",location:" + location);
    }

}
