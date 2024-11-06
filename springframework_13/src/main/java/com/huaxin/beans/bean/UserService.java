package com.huaxin.beans.bean;

import com.huaxin.beans.beandefinitionreader.annotation.Component;
import com.huaxin.beans.beandefinitionreader.annotation.Scope;

import java.util.Random;

@Component
@Scope
public class UserService implements IUserService{
    private String token;
    
    @Override
    public String toString() {
        return "UserService{" +
                "token='" + token + '\'' +
                '}';
    }

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "zhjj and zq";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "注册userName:" + userName;
    }
}
