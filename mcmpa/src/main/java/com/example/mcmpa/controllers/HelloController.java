package com.example.mcmpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    private String users;

    @RequestMapping("/")
    String index() {
        return "index";
    }


}
