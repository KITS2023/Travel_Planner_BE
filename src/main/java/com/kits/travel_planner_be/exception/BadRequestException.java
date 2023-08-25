package com.kits.travel_planner_be.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String message){
        super(message);
    }
}
