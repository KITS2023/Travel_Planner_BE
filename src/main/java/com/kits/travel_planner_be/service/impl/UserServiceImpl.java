package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.UserInfoRequest;
import com.kits.travel_planner_be.repository.UserRepository;
import com.kits.travel_planner_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
    }

    @Override
    public User updateUser(Long id, UserInfoRequest userInfoRequest) {
        User user = getUserById(id);
        user.setFullName(userInfoRequest.getFullName());
        user.setUsername(userInfoRequest.getUsername());
        user.setEmail(userInfoRequest.getEmail());
        user.setProfilePicture(userInfoRequest.getProfilePicture());
        user.setPreferences(userInfoRequest.getPreferences());
        user.setPassword(passwordEncoder.encode(userInfoRequest.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public User getUserByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username or email", username + email));
    }

    @Override
    public Boolean checkUsernameOrEmailExisted(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public String generateRandomPassword() {
        int n = 20;
        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
