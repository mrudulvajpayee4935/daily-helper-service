package com.dailyhelpers.helperservice.controller;

import com.dailyhelpers.helperservice.model.User;
import com.dailyhelpers.helperservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userRepository.findByPhone(user.getPhone())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).body(null));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            return ResponseEntity.status(409).body("User already exists");
        }
        return ResponseEntity.ok(userRepository.save(user));
    }
}
