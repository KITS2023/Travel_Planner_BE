package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.payload.request.ActivityRequest;
import com.kits.travel_planner_be.payload.response.ActivityResponse;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<?> addActivity(@RequestBody @Valid ActivityRequest activityRequest) {
        ActivityResponse activityResponse = activityService.saveActivity(activityRequest);

        ResponseSuccess<ActivityResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Created activity successful.");
        responseSuccess.setData(activityResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable Long id, @RequestBody @Valid ActivityRequest activityRequest) {
        ActivityResponse activityResponse = activityService.updateActivity(id, activityRequest);

        ResponseSuccess<ActivityResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated activity successful.");
        responseSuccess.setData(activityResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivityById(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(true);
        messageResponse.setMessage("Deleted activity with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }


}
