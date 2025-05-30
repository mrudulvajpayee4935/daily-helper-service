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
    public ResponseEntity<User> login(@RequestBody User user) {
        return userRepository.findByPhone(user.getPhone())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok(userRepository.save(user)));
    }
}
