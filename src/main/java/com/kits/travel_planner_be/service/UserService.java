package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.UserInfoRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, UserInfoRequest userInfoRequest);

    void deleteUserById(Long id);

    User getUserByUsernameOrEmail(String username, String email);

    Boolean checkUsernameOrEmailExisted(String username, String email);

    String generateRandomPassword();

    void changePassword(User user, String newPassword);

}
