package com.softworkshub.quizproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/home")
    public String quizPage(){
        return "index";
    }


}
