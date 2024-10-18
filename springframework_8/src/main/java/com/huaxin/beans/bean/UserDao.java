package com.huaxin.beans.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> infoMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行: init-method");
        infoMap.put("10001","zq");
        infoMap.put("10002","zhjj");
    }

    public void destroyDataMethod(){
        System.out.println("执行: destroy-method");
        infoMap.clear();
    }

    public UserDao() {
    }

    public String queryInfo(String id){
        return infoMap.get(id);
    }
}
