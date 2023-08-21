package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.payload.request.DestinationRequest;
import com.kits.travel_planner_be.payload.response.DestinationResponse;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.DestinationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public ResponseEntity<?> getAllDestinations(){
        List<DestinationResponse> destinationResponses = destinationService.getAllDestinations();

        ResponseSuccess<List<DestinationResponse>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("List all destinations.");
        responseSuccess.setData(destinationResponses);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDestinationById(@PathVariable Long id){
        DestinationResponse destinationResponse = destinationService.getDestinationById(id);

        ResponseSuccess<DestinationResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Get destination by id.");
        responseSuccess.setData(destinationResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> addDestination(@RequestBody @Valid DestinationRequest destinationRequest){
        DestinationResponse destinationResponse = destinationService.saveDestination(destinationRequest);

        ResponseSuccess<DestinationResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Created destination successful.");
        responseSuccess.setData(destinationResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addDestination(@PathVariable Long id, @RequestBody @Valid DestinationRequest destinationRequest){
        DestinationResponse destinationResponse = destinationService.updateDestination(id, destinationRequest);

        ResponseSuccess<DestinationResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated destination successful.");
        responseSuccess.setData(destinationResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDestinationById(@PathVariable Long id){
        destinationService.deleteDestinationById(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(true);
        messageResponse.setMessage("Deleted destination with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }


}
