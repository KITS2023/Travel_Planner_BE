package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.payload.request.FlightRequest;
import com.kits.travel_planner_be.payload.response.FlightResponse;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<?> addFlight(@RequestBody @Valid FlightRequest flightRequest){
        FlightResponse flightResponse = flightService.saveFlight(flightRequest);

        ResponseSuccess<FlightResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Created flight successful. ");
        responseSuccess.setData(flightResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight (@PathVariable Long id, @RequestBody @Valid FlightRequest flightRequest){
        FlightResponse flightResponse = flightService.updateFlight(id, flightRequest);

        ResponseSuccess<FlightResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated flight successful. ");
        responseSuccess.setData(flightResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlightById (@PathVariable Long id){
        flightService.deleteFlightById(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(true);
        messageResponse.setMessage("Deleted flight with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
