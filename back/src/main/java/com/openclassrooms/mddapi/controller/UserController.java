package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ProfileDto;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PutMapping("/user/1")
    public Optional<User> updateUser(/*@PathVariable Long id, */@RequestBody ProfileDto profile) {
        return userService.updateUser(profile);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/1/subscribe")
    public Optional<User> subscribe(/*@PathVariable Long id, */@RequestBody Topic topic) {
        return userService.subscribe(topic);
    }

    @PutMapping("/1/unsubscribe")
    public Optional<User> unsubscribe(/*@PathVariable Long id, */@RequestBody Topic topic) {
        return userService.unsubscribe(topic);
    }
}
