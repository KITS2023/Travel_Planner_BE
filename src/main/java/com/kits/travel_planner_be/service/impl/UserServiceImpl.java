package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.repository.UserRepository;
import com.kits.travel_planner_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }
}
