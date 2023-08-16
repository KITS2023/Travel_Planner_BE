package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.exception.BadRequestException;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.LoginRequest;
import com.kits.travel_planner_be.payload.request.RegisterRequest;
import com.kits.travel_planner_be.payload.response.LoginResponse;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.AuthService;
import com.kits.travel_planner_be.service.EmailService;
import com.kits.travel_planner_be.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        String token = authService.login(loginRequest);

        User user = userService.getUserByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());

        LoginResponse<User> loginResponse = new LoginResponse<>();
        loginResponse.setMessage("Login successful");
        loginResponse.setData(user);
        loginResponse.setToken(token);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) throws BadRequestException {
        User user = authService.register(registerRequest);
        ResponseSuccess<User> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Register successful");
        responseSuccess.setData(user);

        return ResponseEntity.ok(responseSuccess);
    }

    @GetMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("usernameOrEmail") String usernameOrEmail) {
        Boolean check = userService.checkUsernameOrEmailExisted(usernameOrEmail, usernameOrEmail);
        MessageResponse messageResponse = new MessageResponse();

        if (check) {
            User user = userService.getUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
            String newPassword = userService.generateRandomPassword();
            userService.changePassword(user, newPassword);
            boolean status = emailService.sendMailResetPassword(newPassword, user.getEmail());
            if(status){
                messageResponse.setSuccess(true);
                messageResponse.setMessage("Sent mail to reset password successful.");
                return new ResponseEntity<>(messageResponse, HttpStatus.OK);
            }else {
                messageResponse.setSuccess(false);
                messageResponse.setMessage("Sent mail to reset password failed.");
                return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
            }
        } else {
            messageResponse.setSuccess(false);
            messageResponse.setMessage("Can not find user with username or email: " + usernameOrEmail);
            return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
        }
    }

}
