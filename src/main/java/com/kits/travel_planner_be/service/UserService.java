package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.User;

import java.util.Optional;

public interface UserService {
    User getUserByUsernameOrEmail(String username, String email);

    Boolean checkUsernameOrEmailExisted(String username, String email);

    String generateRandomPassword();

    void changePassword(User user, String newPassword);

}
