package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.UserInfoRequest;
import com.kits.travel_planner_be.payload.response.PagedResponse;
import com.kits.travel_planner_be.payload.response.UserResponse;
import com.kits.travel_planner_be.repository.UserRepository;
import com.kits.travel_planner_be.service.UserService;
import com.kits.travel_planner_be.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PagedResponse<UserResponse> getAllUsers(int page, int size) {
        PaginationUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<User> users = userRepository.findAll(pageable);

        if (users.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), users.getNumber(), users.getSize(), users.getTotalElements(),
                    users.getTotalPages(), users.isLast());
        }

        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = new UserResponse(user.getId(), user.getFullName(), user.getUsername(), user.getEmail(),
                    user.getProfilePicture(), user.getPreferences(), user.getRole());
            userResponses.add(userResponse);
        }

        return new PagedResponse<>(userResponses, users.getNumber(), users.getSize(), users.getTotalElements(), users.getTotalPages(),
                users.isLast());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));

        return new UserResponse(user.getId(), user.getFullName(), user.getUsername(), user.getEmail(),
                user.getProfilePicture(), user.getPreferences(), user.getRole());
    }

    @Override
    public UserResponse updateUser(Long id, UserInfoRequest userInfoRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));

        user.setFullName(userInfoRequest.getFullName());
        user.setUsername(userInfoRequest.getUsername());
        user.setEmail(userInfoRequest.getEmail());
        user.setProfilePicture(userInfoRequest.getProfilePicture());
        user.setPreferences(userInfoRequest.getPreferences());
        user.setPassword(passwordEncoder.encode(userInfoRequest.getPassword()));
        userRepository.save(user);

        return new UserResponse(user.getId(), user.getFullName(), user.getUsername(), user.getEmail(),
                user.getProfilePicture(), user.getPreferences(), user.getRole());
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));

        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserByUsernameOrEmail(String username, String email) {
        User user = userRepository.findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username or email", username + email));

        return new UserResponse(user.getId(), user.getFullName(), user.getUsername(), user.getEmail(),
                user.getProfilePicture(), user.getPreferences(), user.getRole());
    }

    @Override
    public Boolean checkUsernameOrEmailExisted(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public String generateRandomPassword() {
        int n = 20;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    public void changePassword(String usernameOrEmail, String newPassword) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username or email", usernameOrEmail));
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}
