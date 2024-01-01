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

    /**
     * Route qui va enregistrer un nouvel utilisateur
     * @param user
     * @return
     */
    @PostMapping("/auth/register")
    public ResponseEntity<Object> saveUser(@RequestBody RegisterDto user) {
        try {
            JwtTokenDto newUser = userService.register(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va connecter un utilisateur
     * @param user
     * @return
     */
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto user) {
        try {
            JwtTokenDto newUser = userService.login(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va récupérer les informations de l'utilisateur connecté
     * @param request
     * @return
     */
    @GetMapping("/auth/me")
    public ResponseEntity<Long> me(HttpServletRequest request) {
        try {
            Long user = userService.me(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Route qui va mettre à jour les informations de l'utilisateur
     * @param id
     * @param profile
     * @return
     */
    @PutMapping("/user/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody ProfileDto profile) {
        return userService.updateUser(profile, id);
    }

    /**
     * Route qui va récupérer un utilisateur à partir de son id
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * Route qui va récupérer tous les utilisateurs
     * @return Iterable<User>
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * Route qui va ajouter un abonnement à un utilisateur
     * @param id
     * @return Iterable<Topic>
     */
    @PutMapping("/{id}/subscribe")
    public Optional<User> subscribe(@PathVariable Long id, @RequestBody Topic topic) {
        return userService.subscribe(id, topic);
    }

    /**
     * Route qui va supprimer un abonnement à un utilisateur
     * @param id
     * @return Iterable<Topic>
     */
    @PutMapping("/{id}/unsubscribe")
    public Optional<User> unsubscribe(@PathVariable Long id, @RequestBody Topic topic) {
        return userService.unsubscribe(id, topic);
    }
}
