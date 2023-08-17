package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.TripRequest;

import com.kits.travel_planner_be.payload.response.ActivityResponse;
import com.kits.travel_planner_be.payload.response.TripResponse;
import com.kits.travel_planner_be.payload.response.FlightResponse;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;

import com.kits.travel_planner_be.service.FlightService;
import com.kits.travel_planner_be.service.ActivityService;
import com.kits.travel_planner_be.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private FlightService flightService;

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<?> addTrip(@RequestBody @Valid TripRequest tripRequest) {
        TripResponse tripResponse = tripService.saveTrip(tripRequest);

        ResponseSuccess<TripResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Created trip successful.");
        responseSuccess.setData(tripResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody @Valid TripRequest tripRequest) {
        TripResponse tripResponse = tripService.updateTrip(id, tripRequest);

        ResponseSuccess<TripResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated trip successful.");
        responseSuccess.setData(tripResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTripById(@PathVariable Long id){
        tripService.deleteTripById(id);
        MessageResponse messageResponse = new MessageResponse(true, "Deleted trip with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }


    @GetMapping("/{tripId}/activities")
    public ResponseEntity<?> getALlActivitiesByTrip(@PathVariable Long tripId) {
        List<ActivityResponse> trips = activityService.getAllActivitiesByTrip(tripId);

        ResponseSuccess<List<ActivityResponse>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("List all activity by trip.");
        responseSuccess.setData(trips);

    @GetMapping("/{tripId}/flights")
    public ResponseEntity<?> getAllFlightsByTrip(@PathVariable("tripId") Long tripId){
        List<FlightResponse> flightResponses = flightService.getAllFlightsByTrip(tripId);

        ResponseSuccess<List<FlightResponse>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("List all flights by trip.");
        responseSuccess.setData(flightResponses);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}
