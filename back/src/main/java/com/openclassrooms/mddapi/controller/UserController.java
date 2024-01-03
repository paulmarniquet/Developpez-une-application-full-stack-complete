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
     *
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
     *
     * @param user
     * @return
     */
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto user) {
        try {
            JwtTokenDto newUser = userService.login(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Route qui va récupérer les informations de l'utilisateur connecté
     *
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
     *
     * @param id
     * @param profile
     * @return
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @Valid @RequestBody ProfileDto profile) {
        try {
            User user = userService.updateUser(profile, id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Informations invalides");
        }
    }

    /**
     * Route qui va récupérer un utilisateur à partir de son id
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Route qui va récupérer tous les utilisateurs
     *
     * @return Iterable<User>
     */
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        try {
            Iterable<User> user = userService.getUsers();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Route qui va ajouter un abonnement à un utilisateur
     *
     * @param id
     * @return Iterable<Topic>
     */
    @PutMapping("/{id}/subscribe")
    public ResponseEntity<User> subscribe(@PathVariable Long id, @RequestBody Topic topic) {
        try {
            User user = userService.subscribe(id, topic);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Route qui va supprimer un abonnement à un utilisateur
     *
     * @param id
     * @return Iterable<Topic>
     */
    @PutMapping("/{id}/unsubscribe")
    public ResponseEntity<User> unsubscribe(@PathVariable Long id, @RequestBody Topic topic) {
        try {
            User user = userService.unsubscribe(id, topic);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
