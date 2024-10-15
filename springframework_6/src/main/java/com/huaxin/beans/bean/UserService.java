package com.huaxin.beans.bean;

public class UserService {
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
}
