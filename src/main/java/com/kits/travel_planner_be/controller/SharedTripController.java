package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.payload.request.SharedTripRequest;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.payload.response.SharedTripResponse;
import com.kits.travel_planner_be.service.SharedTripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharedtrips")
public class SharedTripController {
    @Autowired
    private SharedTripService sharedTripService;
    @PostMapping
    public ResponseEntity<?> addSharedTrip(@RequestBody @Valid SharedTripRequest sharedTripRequest){
        SharedTripResponse sharedTripResponse = sharedTripService.saveSharedTrip(sharedTripRequest);
        ResponseSuccess<SharedTripResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Created shared trip successful. ");
        responseSuccess.setData(sharedTripResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSharedTrip (@PathVariable Long id, @RequestBody @Valid SharedTripRequest sharedTripRequest){
        SharedTripResponse sharedTripResponse = sharedTripService.updateSharedTrip(id, sharedTripRequest);
        ResponseSuccess<SharedTripResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated shared trip successful. ");
        responseSuccess.setData(sharedTripResponse);
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSharedTripById (@PathVariable Long id){
        sharedTripService.deleteSharedTripById(id);
        MessageResponse messageResponse = new MessageResponse(true, "Deleted shared trip with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
