package com.jpa.jpa.service;

import com.jpa.jpa.exception.UserAlreadyExistException;
import com.jpa.jpa.model.User;
import com.jpa.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email, String password) throws UserAlreadyExistException {
        Optional<User> optUser = userRepository.getUserByEmail(email);
        if (!optUser.isPresent()) {
            User user = new User(email,password);
            return userRepository.save(user);
        }else {
            throw new UserAlreadyExistException("User at email: " + email + " already exist");
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
