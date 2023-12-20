package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ProfileDto;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

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
}
