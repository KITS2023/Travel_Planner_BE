package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.BadRequestException;
import com.kits.travel_planner_be.exception.UnauthenticatedException;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.LoginRequest;
import com.kits.travel_planner_be.payload.request.RegisterRequest;
import com.kits.travel_planner_be.repository.UserRepository;
import com.kits.travel_planner_be.security.jwt.JwtTokenProvider;
import com.kits.travel_planner_be.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            return token;
        }catch (AuthenticationException e){
            throw new UnauthenticatedException("Username or password is wrong");
        }

    }

    @Override
    public User register(RegisterRequest registerRequest) throws BadRequestException {

        // add check for username exists in a DB
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            throw new BadRequestException("Username is already taken!");
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new BadRequestException("Email is already taken!");
        }

        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return user;
    }
}
