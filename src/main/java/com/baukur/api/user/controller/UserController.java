package com.baukur.api.user.controller;

import com.baukur.api.user.domain.User;
import com.baukur.api.user.domain.UserDetailsImpl;
import com.baukur.api.user.service.BaukurUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private BaukurUserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User preEx = userDetailsService.getUserByEmail(user.getEmail());
            if (preEx != null) {
                return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDetailsService.createUser(user);
            return new ResponseEntity<>("New user registered", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to register user", e);
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            userDetailsService.deleteUser(user.getId());
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to delete user", e);
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> editUsername(@AuthenticationPrincipal UserDetailsImpl user, String newEmail) {
        try {
            userDetailsService.editUsername(user.getId(), newEmail);
            return new ResponseEntity<>("Username updated", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to update username", e);
            return new ResponseEntity<>("Failed to update username", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> editUser(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody User newUser) {
        try {
            newUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDetailsService.editUser(user.getId(), newUser);
            return new ResponseEntity<>("User updated", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to update user", e);
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
