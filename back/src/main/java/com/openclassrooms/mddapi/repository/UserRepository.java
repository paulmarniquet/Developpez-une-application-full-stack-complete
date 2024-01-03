package com.openclassrooms.mddapi.repository;

import org.springframework.data.repository.CrudRepository;
import com.openclassrooms.mddapi.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByName(String name);

}