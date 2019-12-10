package com.formAuthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@RestController
@RequestMapping("api")
public class RestApiController {

    @GetMapping("test1")
    public String test1(){
        return "Test1";
    }

    @GetMapping("test2")
    public String test2(){
        return "test2";
    }
}
