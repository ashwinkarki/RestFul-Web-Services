package com.rest.restfulwebservices.restfulwebservices.service;

import com.rest.restfulwebservices.restfulwebservices.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    private static List<User> userList=new ArrayList<>();

    public static int usersCount=3;

    static{
        userList.add(new User(1,"Adam",new Date()));
        userList.add(new User(2,"Anil",new Date()));
        userList.add(new User(3,"Amar",new Date()));
    }

    public List<User> findAllUser(){
        return userList;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        userList.add(user);
        return  user;
    }

    public User findOne(int id){
        for(User user:userList){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator=userList.iterator();
       while(iterator.hasNext()){
           User user=iterator.next();
            if(user.getId()==id){
                iterator.remove();
            }
        }
        return null;
    }
}
