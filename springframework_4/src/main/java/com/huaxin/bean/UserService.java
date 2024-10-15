package com.huaxin.bean;

public class UserService {
    private String company;
    private String id;
    private UserDao userDao;

    public UserService(String company){
        this.company = company;
    }

    public void queryUserinfo(){
        System.out.println("company:" + this.company + "," +
                "id:" + this.id + "," +
                "name:" + userDao.queryUser(id));
    }
}
