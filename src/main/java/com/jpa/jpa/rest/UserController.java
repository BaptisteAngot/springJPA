package com.jpa.jpa.rest;

import com.jpa.jpa.exception.UserAlreadyExistException;
import com.jpa.jpa.model.User;
import com.jpa.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUser();
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> register(@RequestBody @Valid User user) throws UserAlreadyExistException {
        try {
            return new ResponseEntity<User>(userService.getUserByEmail(user.getEmail(),user.getPassword()),HttpStatus.OK);
        }catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
