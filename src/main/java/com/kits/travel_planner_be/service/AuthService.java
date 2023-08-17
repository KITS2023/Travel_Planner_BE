package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.exception.BadRequestException;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.LoginRequest;
import com.kits.travel_planner_be.payload.request.RegisterRequest;
import com.kits.travel_planner_be.payload.response.UserResponse;

public interface AuthService {
    String login(LoginRequest loginRequest);
    UserResponse register(RegisterRequest registerRequest) throws BadRequestException;
}
