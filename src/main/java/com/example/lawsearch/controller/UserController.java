package com.example.lawsearch.controller;

import com.example.lawsearch.model.AppUser;
import com.example.lawsearch.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository appUserRepository;

    public UserController(UserRepository userRepository) {
        this.appUserRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser appUser) {
        appUser.setPassword(appUser.getPassword());
        appUser.setRole("USER");
        appUserRepository.save(appUser);
        return ResponseEntity.ok("User registered successfully");
    }
}