package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Route qui va enregistrer un nouvel utilisateur
     * @param user
     * @return
     */
    @PostMapping("/auth/register")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody RegisterDto user) {
        try {
            JwtTokenDto newUser = userService.register(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * Route qui va connecter un utilisateur
     * @param user
     * @return
     */
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto user) {
        if (user.getEmailOrUsername() == null || user.getEmailOrUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Veuillez fournir un email/nom d'utilisateur et un mot de passe valides.");
        }

        try {
            JwtTokenDto newUser = userService.login(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
