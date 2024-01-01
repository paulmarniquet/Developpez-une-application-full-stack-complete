package com.openclassrooms.mddapi.repository;

import org.springframework.data.repository.CrudRepository;
import com.openclassrooms.mddapi.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

}