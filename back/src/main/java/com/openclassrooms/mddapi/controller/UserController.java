package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Object> saveUser(@RequestBody RegisterDto user) {
        try {
            JwtTokenDto newUser = userService.register(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto user) {
        try {
            JwtTokenDto newUser = userService.login(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/auth/me")
    public ResponseEntity<Long> me(HttpServletRequest request) {
        try {
            Long user = userService.me(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PutMapping("/user/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody ProfileDto profile) {
        return userService.updateUser(profile, id);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{id}/subscribe")
    public Optional<User> subscribe(@PathVariable Long id, @RequestBody Topic topic) {
        return userService.subscribe(id, topic);
    }

    @PutMapping("/{id}/unsubscribe")
    public Optional<User> unsubscribe(@PathVariable Long id, @RequestBody Topic topic) {
        return userService.unsubscribe(id, topic);
    }
}
