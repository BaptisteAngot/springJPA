package com.jpa.jpa.mvc;

import com.jpa.jpa.model.User;
import com.jpa.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/mvc")
public class UserControllerMvc {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public String getUserById(@RequestParam(name = "id", required = false) Long id, ModelMap model) {
        List<User> users = new ArrayList<User>();
        if (id != null) {
            User user = userService.getUserById(id);
            users.add(user);
        }else {
            List<User> list = userService.getAllUser();
            users.addAll(list);
        }
        model.addAttribute("users",users);
        return "user";
    }

}
