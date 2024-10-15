package com.huaxin.beans.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> infoMap = new HashMap<>();

    static{
        infoMap.put("10001","zhjj");
        infoMap.put("10002","zq");
        infoMap.put("10003","others");
    }

    public UserDao() {
    }

    public String queryInfo(String id){
        return infoMap.get(id);
    }
}
