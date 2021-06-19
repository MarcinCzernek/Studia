package com.example.myspa.controllers;

import com.example.myspa.model.User;
import com.example.myspa.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list(){
        return new ResponseEntity(userService.get(), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody User user){
        return new ResponseEntity(userService.add(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User user) {
        User newUser = userService.update(id,user.getName());
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        Optional<User> user = userService.get(id);

        if (user.isPresent()){
            return new ResponseEntity(user.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


}
