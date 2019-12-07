package com.formAuthentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Controller
@RequestMapping("profile")
public class ProfileController {

    @GetMapping("index")
    public String index(){
        return "profile/index";
    }
}
