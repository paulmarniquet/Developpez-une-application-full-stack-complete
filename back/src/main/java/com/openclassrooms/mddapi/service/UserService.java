package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.config.JwtTokenProvider;
import com.openclassrooms.mddapi.dto.JwtTokenDto;
import com.openclassrooms.mddapi.dto.ProfileDto;
import com.openclassrooms.mddapi.dto.RegisterDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(ProfileDto profile) {
        /*Long id = profile.getId();*/
        Long id = 1L;
        User user = userRepository.findById(id).get();
        user.setName(profile.getName());
        user.setEmail(profile.getEmail());
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> subscribe(Topic topic) {
        Long id = 1L;
        User user = userRepository.findById(id).get();
        user.getTopics().add(topic);
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> unsubscribe(Topic topic) {
        Long id = 1L;
        User user = userRepository.findById(id).get();
        user.getTopics().remove(topic);
        return Optional.of(userRepository.save(user));
    }

    public JwtTokenDto register(RegisterDto request) {
        if (userRepository.findByEmail(request.email).isPresent()) {
            throw new RuntimeException("User already exists");
        } else {
            User user = new User();
            user.setEmail(request.email);
            user.setName(request.name);
            user.setPassword(passwordEncoder.encode(String.valueOf(CharBuffer.wrap(request.getPassword()))));
            userRepository.save(user);
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            return new JwtTokenDto(new JwtTokenProvider().generateToken(userDto));
        }
    }
}
