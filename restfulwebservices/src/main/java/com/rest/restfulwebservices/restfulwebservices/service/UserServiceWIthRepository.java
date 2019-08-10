package com.rest.restfulwebservices.restfulwebservices.service;

import com.rest.restfulwebservices.restfulwebservices.exception.UserNotFoundException;
import com.rest.restfulwebservices.restfulwebservices.model.User;
import com.rest.restfulwebservices.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceWIthRepository {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUser()
    {
        return userRepository.findAll();
    }

    public User save(User user){
        if(user.getId()==null){
            userRepository.save(user);
        }
        else{
            userRepository.save(user);
        }
                return  user;
    }

    public User findOne(int id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
         return null;
    }

    public void deleteById(int id){
          userRepository.deleteById(id);
    }
}
