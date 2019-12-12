package com.formAuthentication.controller;

import com.formAuthentication.persistence.model.User;
import com.formAuthentication.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@RestController
@RequestMapping("api")
public class RestApiController {

    private @Autowired UserRepository userRepository;

    @GetMapping("test1")
    public String test1(){
        return "Test1";
    }

    @GetMapping("test2")
    public String test2(){
        return "test2";
    }

    //TODO: WHILE accessing 3 select Query
    @GetMapping("all-users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }
}
