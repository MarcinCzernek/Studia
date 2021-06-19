package com.example.myspa.services;


import com.example.myspa.model.User;
import com.example.myspa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public Long add(User user){
        return userRepository.save(user).getId();
    }

    @Transactional
    public void delete(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        }
    }

    @Transactional
    public Optional<User> get (Long id){
        return userRepository.findById(id);

    }

    @Transactional
    public List<User> get(){
        return userRepository.findAll();
    }

    @Transactional
    public User update(Long id, String name){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setName(name);
            userRepository.save(user.get());
        }
        return null;
    }
}
