package com.zack.objson;

import java.util.ArrayList;
import java.util.List;

public class User {

    String name;

    String pwd;

    int age;

    boolean ignore;
    String address;

    List<User> users;
    List<String> list;
    String[] array;
    public User(String name, String pwd, int age, boolean ignore, String address) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
        this.ignore = ignore;
        this.address = address;
        list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        list.add("fff");
        list.add("ggg");
       /* array = new String[3];
        array[0] = "arr0";
        array[1] = "arr1";
        array[2] = "arr2";*/
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
