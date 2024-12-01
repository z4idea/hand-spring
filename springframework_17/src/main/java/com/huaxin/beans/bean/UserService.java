package com.huaxin.beans.bean;

import java.util.Random;

public class UserService implements IUserService{
    
    private String token;

    @Override
    public String queryInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
