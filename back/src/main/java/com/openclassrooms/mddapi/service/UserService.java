package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.config.JwtTokenProvider;
import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.CharBuffer;
import java.util.Optional;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     * Récupère un utilisateur à partir de son id
     * @param id
     * @return
     */
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }


    /**
     * Récupère tous les utilisateurs
     * @return
     */
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Met à jour les informations d'un utilisateur
     * @param profile
     * @param id
     * @return
     */
    public Optional<User> updateUser(ProfileDto profile, Long id) {
        User user = userRepository.findById(id).get();
        user.setName(profile.getName());
        user.setEmail(profile.getEmail());
        return Optional.of(userRepository.save(user));
    }


    /**
     * Abonne un utilisateur à un topic
     * @param id
     * @param topic
     */
    public Optional<User> subscribe(Long id, Topic topic) {
        User user = userRepository.findById(id).get();
        user.getTopics().add(topic);
        return Optional.of(userRepository.save(user));
    }


    /**
     * Désabonne un utilisateur à un topic
     * @param id
     * @param topic
     */
    public Optional<User> unsubscribe(Long id, Topic topic) {
        User user = userRepository.findById(id).get();
        user.getTopics().remove(topic);
        return Optional.of(userRepository.save(user));
    }


    /**
     * Enregistre un utilisateur
     * @param request
     * @return
     */
    public JwtTokenDto register(RegisterDto request) {
        if (userRepository.findByEmail(request.email).isPresent() || userRepository.findByName(request.name).isPresent()) {
            throw new RuntimeException("User already exists");
        } else {
            User user = new User();
            user.setEmail(request.email);
            user.setName(request.name);
            user.setPassword(passwordEncoder.encode(String.valueOf(CharBuffer.wrap(request.getPassword()))));
            userRepository.save(user);
            LoginDto loginDto = new LoginDto();
            loginDto.setEmailOrUsername(request.email);
            loginDto.setPassword(request.getPassword());
            return new JwtTokenDto(new JwtTokenProvider().generateToken(loginDto));
        }
    }


    /**
     * Connecte un utilisateur
     * @param request
     * @return
     */
    public JwtTokenDto login(LoginDto request) {
        User userByEmail = userRepository.findByEmail(request.getEmailOrUsername())
                .orElse(null);

        User userByUsername = userRepository.findByName(request.getEmailOrUsername())
                .orElse(null);

        User user = (userByEmail != null) ? userByEmail : userByUsername;

        if (user != null && passwordEncoder.matches(CharBuffer.wrap(request.getPassword()), user.getPassword())) {
            LoginDto loginDto = new LoginDto();
            loginDto.setEmailOrUsername(user.getEmail());
            loginDto.setPassword(user.getPassword());
            return new JwtTokenDto(new JwtTokenProvider().generateToken(loginDto));
        } else {
            throw new RuntimeException("User not found or password is incorrect");
        }
    }


    /**
     * Extrait le token de la requête
     * @param request
     * @return token
     */
    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Récupère l'id de l'utilisateur connecté
     * @param request
     * @return
     */
    public Long me(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        String email = new JwtTokenProvider().getUsernameFromToken(token);
        Optional<User> user = userRepository.findByEmail(email);
        return user.get().getId();
    }
}
