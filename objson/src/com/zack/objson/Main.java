package com.zack.objson;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
      /*  User user1 = new User("name_1","pwd_1",101,false, "address_1");
        User user2 = new User("name_2","pwd_2",102,false, "address_2");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        User user = new User("name_","pwd_",100,false, "address_", users);
        SimpleJson simpleJson = new SimpleJson();
        System.out.println(simpleJson.toJson(user));*/

        User user1 = new User("name_1","pwd_1",101,false, "address_1");
        User user2 = new User("name_2","pwd_2",102,false, "address_2");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        User user = new User("name_","pwd_",100,false, "address_", users);
        SimpleJson simpleJson = new SimpleJson();
        System.out.println(simpleJson.toJson(user));
        //System.out.println(simpleJson.toJson(users));
        String[] aaa = new String[4];
        Object[] oarr = aaa;
        System.out.println((oarr instanceof String[]));
    }
}
