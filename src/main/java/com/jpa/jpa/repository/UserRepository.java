package com.jpa.jpa.repository;

import com.jpa.jpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findUserById(Long id);
    User findByFirstname(String string);
    Optional<User> getUserByEmail(String email);
}
