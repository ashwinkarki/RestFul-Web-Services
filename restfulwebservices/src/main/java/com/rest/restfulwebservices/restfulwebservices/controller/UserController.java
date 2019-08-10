package com.rest.restfulwebservices.restfulwebservices.controller;

import com.rest.restfulwebservices.restfulwebservices.exception.UserNotFoundException;
import com.rest.restfulwebservices.restfulwebservices.model.User;
import com.rest.restfulwebservices.restfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> retrieveAllUser(){
       return userService.findAllUser();
    }


    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
       User user=userService.findOne(id);
       if(user==null){
           throw new UserNotFoundException("id -"+id);
       }
       return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid  @RequestBody  User user){
      User savedUser= userService.save(user);

      //return status of HTtp after created
       URI location= ServletUriComponentsBuilder .fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user= userService.deleteById(id);
        //return status of HTtp after created
        if(user==null){
            throw new UserNotFoundException("id -"+id);
        }

    }
}
