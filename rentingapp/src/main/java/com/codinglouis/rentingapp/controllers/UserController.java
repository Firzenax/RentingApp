package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.exceptions.UserNotFoundException;
import com.codinglouis.rentingapp.models.User;
import com.codinglouis.rentingapp.repositories.RentRepository;
import com.codinglouis.rentingapp.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, RentRepository rentRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("{user_id}")
    public Optional<User> getUserById(
            @PathVariable("user_id") Integer user_id) throws Exception {
        try {
            return userRepository.findById(user_id);
        } catch (Error error) {
            throw new Exception(error.getMessage() + "user doesn't exist");
        }
    }

    @PostMapping
    public User createUser(
            @RequestBody User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (Error error) {
            throw new Exception(error.getMessage() + "user doesn't exist");
        }
    }

    @GetMapping("/login")
    public Boolean signIn(@RequestBody User user) throws Exception {
        User searched_user = userRepository.findUserByEmail(user.getEmail());
        if (searched_user != null) {
            return true;
        } else {
            throw new UserNotFoundException("No user with this email: " + user.getEmail());
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
