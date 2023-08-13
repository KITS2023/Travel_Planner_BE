package com.kits.travel_planner_be.exception;


import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String message){
        super(message);
    }

}
