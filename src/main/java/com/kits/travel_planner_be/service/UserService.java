package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
