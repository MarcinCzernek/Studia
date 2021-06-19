package com.example.mc.app.wideoteka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WideotekaController {


    @RequestMapping(value = {"/"})
    public String getADirectors() {
        return "index";
    }

}