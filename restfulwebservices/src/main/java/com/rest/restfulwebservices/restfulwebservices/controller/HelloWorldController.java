package com.rest.restfulwebservices.restfulwebservices.controller;

import com.rest.restfulwebservices.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired //its for iternationaliztion enlgihs,french
    private MessageSource messageSource;

    //GET
    //URI
    //method -"hello world"
    @GetMapping(value = "/hello-world")
    public String helloWorld(){
        return "Hello world";
    }

    @GetMapping(value = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(value = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(name);
    }

    @GetMapping(value = "/hello-world/internationlization")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language",required = false)  Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
    }



}
