package com.zack.objson;

import com.zack.objson.annotation.Alias;
import com.zack.objson.annotation.Ignore;
import com.zack.objson.annotation.SerializeName;

import java.util.List;

public class User {
    @Alias("upwd")
    @SerializeName("uname")
    String name;
    @Alias("upwd")
    String pwd;
    @SerializeName("uage")
    int age;
    @Ignore
    boolean ignore;
    String address;

    @SerializeName("friend")
    List<User> users;

    public User(String name, String pwd, int age, boolean ignore, String address) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
        this.ignore = ignore;
        this.address = address;
    }

    public User(String name, String pwd, int age, boolean ignore, String address, List<User> users) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
        this.ignore = ignore;
        this.address = address;
        this.users = users;
    }
}
