package formBased.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/")
    public String hello(Model model){
        model.addAttribute("message","Welcome to Spring Security Form Based Example!");
        model.addAttribute("date",LocalDate.now());
        return "index";
    }
}
