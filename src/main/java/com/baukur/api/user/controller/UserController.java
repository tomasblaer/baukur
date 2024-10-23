package com.baukur.api.user.controller;

import com.baukur.api.user.domain.User;
import com.baukur.api.user.service.BaukurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BaukurUserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDetailsService.createUser(user);
            return new ResponseEntity<>("New user registered", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
