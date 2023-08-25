package com.kits.travel_planner_be.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthenticatedException extends AuthenticationException {
    public UnauthenticatedException(String message){
        super(message);
    }
}
