package com.huaxin.bean;

// 测试Bean
public class UserService {
    private String name;
    private Integer id;

    public UserService(String id, String name){
        this.name = name;
        this.id = Integer.parseInt(id);
    }

    public UserService(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public void queryInfo(){
        System.out.println("正在查询,名字为" + this.name +",id为" + this.id + "的信息......");
    }
}
