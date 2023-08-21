package com.kits.travel_planner_be.controller;


import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.UserInfoRequest;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.payload.response.TripResponse;
import com.kits.travel_planner_be.payload.response.UserResponse;
import com.kits.travel_planner_be.service.SharedTripService;
import com.kits.travel_planner_be.service.TripService;
import com.kits.travel_planner_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private SharedTripService sharedTripService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();

        ResponseSuccess<List<UserResponse>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("List all users.");
        responseSuccess.setData(userResponses);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);

        ResponseSuccess<UserResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Get user by id.");
        responseSuccess.setData(userResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserInfoRequest userInfoRequest) {
        UserResponse userResponse = userService.updateUser(id, userInfoRequest);

        ResponseSuccess<UserResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated user successful.");
        responseSuccess.setData(userResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(true);
        messageResponse.setMessage("Deleted user with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}/trips")
    public ResponseEntity<?> getAllTripsByUser(@PathVariable("userId") Long userId) {
        List<TripResponse> trips = tripService.getAllTripsByUser(userId);

        ResponseSuccess<List<TripResponse>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("List all trips by user.");
        responseSuccess.setData(trips);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);

    }

    @GetMapping("/{userId}/sharedtrips")
    public ResponseEntity<?> getTripsThatUserIsShared(@PathVariable("userId") Long userId){
        List<TripResponse> trips = sharedTripService.getTripsThatUserIsShared(userId);

        ResponseSuccess<List<TripResponse>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("List all trips by shared user. ");
        responseSuccess.setData(trips);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}
