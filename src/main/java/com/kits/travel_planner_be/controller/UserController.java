package com.kits.travel_planner_be.controller;


import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public User getUserByUsername(@RequestParam(value = "username") String username){
        return userService.getUserByUsernameOrEmail(username, username);
    }
}
