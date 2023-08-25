package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.UserInfoRequest;
import com.kits.travel_planner_be.payload.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserInfoRequest userInfoRequest);

    void deleteUserById(Long id);

    UserResponse getUserByUsernameOrEmail(String username, String email);

    Boolean checkUsernameOrEmailExisted(String username, String email);

    String generateRandomPassword();

    void changePassword(String usernameOrEmail, String newPassword);

}
