package com.yoodaengs.springboot.spring_sercurity_example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

