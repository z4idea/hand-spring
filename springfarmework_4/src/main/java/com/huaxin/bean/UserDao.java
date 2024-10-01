package com.huaxin.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> userMap = new HashMap<>();

    static {
        userMap.put("01","zq");
        userMap.put("02","zhjj");
    }

    public String queryUser(String id){
        return userMap.get(id);
    }

}
