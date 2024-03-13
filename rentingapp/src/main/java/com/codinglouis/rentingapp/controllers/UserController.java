package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.User;
import com.codinglouis.rentingapp.repositories.RentRepository;
import com.codinglouis.rentingapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    private final RentRepository rentRepository;

    public UserController(UserRepository userRepository, RentRepository rentRepository) {
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }
    @GetMapping
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("{user_id}")
    public Optional<User> getUserById(
            @PathVariable("user_id") Integer user_id
    ) throws Exception{
        try{
            return userRepository.findById(user_id);
        }catch (Error error){
            throw new Exception(error.getMessage() + "user doesn't exist");
        }
    }
    @PostMapping
    public User createUser(
            @RequestBody User user
    )throws Exception{
        try{
            return userRepository.save(user);
        }catch (Error error){
            throw new Exception(error.getMessage() + "user doesn't exist");
        }
    }
}
