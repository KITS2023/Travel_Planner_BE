package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.AccommodationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodations")

public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;
    @PostMapping
    private ResponseEntity<?> saveAccommodation (@RequestBody @Valid AccommodationRequest accommodationRequest){
        Accommodation accommodation = accommodationService.saveAccommodation(accommodationRequest);
        ResponseSuccess<Accommodation> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Save accommodation successful!");
        responseSuccess.setData(accommodation);
        return ResponseEntity.ok(responseSuccess);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateAccommodation (@PathVariable("id") Long id, @RequestBody @Valid AccommodationRequest accommodationRequest){
        Accommodation accommodation = accommodationService.updateAccommodation(id,accommodationRequest);
        ResponseSuccess<Accommodation> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Update accommodation successful!");
        responseSuccess.setData(accommodation);
        return ResponseEntity.ok(responseSuccess);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteAccommodationById (@PathVariable("id") Long id){
        accommodationService.deleteAccommodation(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(true);
        messageResponse.setMessage("Deleted accommodation with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
