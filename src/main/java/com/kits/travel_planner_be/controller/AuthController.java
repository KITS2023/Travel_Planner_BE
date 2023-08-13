package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.exception.BadRequestException;
import com.kits.travel_planner_be.exception.UserNotFoundException;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.LoginRequest;
import com.kits.travel_planner_be.payload.request.RegisterRequest;
import com.kits.travel_planner_be.payload.response.LoginResponse;
import com.kits.travel_planner_be.payload.response.ResponseError;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.AuthService;
import com.kits.travel_planner_be.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest loginRequest) throws UserNotFoundException {
        String token = authService.login(loginRequest);

        Optional<User> user = userService.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());
        if (!user.isPresent()) throw new UserNotFoundException("Username or password is wrong!");

        LoginResponse<User> loginResponse = new LoginResponse<>();
        loginResponse.setMessage("Login successful");
        loginResponse.setData(user.get());
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
}
