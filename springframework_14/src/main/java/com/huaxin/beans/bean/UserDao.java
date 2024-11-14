package com.huaxin.beans.bean;

import com.huaxin.beans.beandefinitionreader.annotation.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "zhjj，天津");
        hashMap.put("10002", "zq，天津");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
