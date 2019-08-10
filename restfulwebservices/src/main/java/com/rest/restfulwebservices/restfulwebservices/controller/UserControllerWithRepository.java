package com.rest.restfulwebservices.restfulwebservices.controller;

import com.rest.restfulwebservices.restfulwebservices.exception.UserNotFoundException;
import com.rest.restfulwebservices.restfulwebservices.model.Post;
import com.rest.restfulwebservices.restfulwebservices.model.User;
import com.rest.restfulwebservices.restfulwebservices.repository.PostRepository;
import com.rest.restfulwebservices.restfulwebservices.service.UserService;
import com.rest.restfulwebservices.restfulwebservices.service.UserServiceWIthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserControllerWithRepository {

    @Autowired
    private UserServiceWIthRepository userService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUser(){
       return userService.findAllUser();
    }


    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id){
       User user=userService.findOne(id);
       if(user==null){
           throw new UserNotFoundException("id -"+id);
       }
       return user;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid  @RequestBody  User user){
      User savedUser= userService.save(user);

      //return status of HTtp after created
       URI location= ServletUriComponentsBuilder .fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
        //return status of HTtp after created
    }


    //yesma user ko id pataera post matra tanne function
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserWithPost(@PathVariable int id){
        User userOptional=userService.findOne(id);
        if(userOptional==null){
            throw  new UserNotFoundException("id-"+id);
        }
        return userOptional.getPosts();
    }


    //yesma user tanda post ni tandincha
    @GetMapping("/jpa/usersonly/{id}")
    public User retrieveUserWithPostDoingMyself(@PathVariable int id){
        User userOptional=userService.findOne(id);
        if(userOptional==null){
            throw  new UserNotFoundException("id-"+id);
        }
        return userOptional;
    }


    //yesma user ko id pataune post ni pataune
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createUserWithPost(@PathVariable int id, @RequestBody Post post){
        User savedUser= userService.findOne(id);

        if(savedUser==null){
            throw  new UserNotFoundException("id-"+id);
        }

        post.setUser(savedUser);
        postRepository.save(post);

        //return status of HTtp after created
        URI location= ServletUriComponentsBuilder .fromCurrentRequest().path("/{id}").
                buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
