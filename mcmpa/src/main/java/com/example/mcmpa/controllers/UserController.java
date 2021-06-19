package com.example.mcmpa.controllers;

import com.example.mcmpa.model.User;
import com.example.mcmpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    String users(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @RequestMapping("/{id}")
    String user(@PathVariable("id") Long id, Model model) {
        User user = new User();

        if (id == 0) {
            user.setName("Marcin");
        } else {
            user.setName("Darek");
        }

        model.addAttribute("users", user);
        return "user";

    }

    @RequestMapping("/delete")
    String delete(@RequestParam(value = "name", required = true) String name, Model model) {
        userService.delete(name);
        return users(model);
    }


    @RequestMapping("/add")
    String add(@RequestParam(value = "user", required = true) String name, Model model) {
        userService.add(name);
        return users(model);
    }


}
