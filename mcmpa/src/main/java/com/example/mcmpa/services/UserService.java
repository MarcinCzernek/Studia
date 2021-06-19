package com.example.mcmpa.services;


import com.example.mcmpa.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public void add(String name) {
        User newUser = new User();
        newUser.setName(name);
        users.add(newUser);

    }

    public void delete(String name) {
        for (User user : users) {
            if (user.getName().equals(name))
                users.remove(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }

}
