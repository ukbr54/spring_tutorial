package com.formAuthentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Controller
public class HomeController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
