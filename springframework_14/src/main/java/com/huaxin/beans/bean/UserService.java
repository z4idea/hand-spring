package com.huaxin.beans.bean;

import com.huaxin.beans.beandefinitionreader.annotation.Autowired;
import com.huaxin.beans.beandefinitionreader.annotation.Component;
import com.huaxin.beans.beandefinitionreader.annotation.Value;

import java.util.Random;

@Component("userService")
public class UserService {
    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "," + token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
