package com.huaxin.beans.bean;


import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "zhjj");
        hashMap.put("10002", "zq");
    }

    public UserDao() {
    }

    public String queryInfo(String id){
        return hashMap.get(id);
    }
}
