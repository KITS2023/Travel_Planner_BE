package com.kits.travel_planner_be.controller;

import com.kits.travel_planner_be.payload.request.ReviewRequest;
import com.kits.travel_planner_be.payload.response.MessageResponse;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.payload.response.ReviewResponse;
import com.kits.travel_planner_be.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody @Valid ReviewRequest reviewRequest){
        ReviewResponse reviewResponse = reviewService.saveReview(reviewRequest);

        ResponseSuccess<ReviewResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Created review successful. ");
        responseSuccess.setData(reviewResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody @Valid ReviewRequest reviewRequest){
        ReviewResponse reviewResponse = reviewService.updateReview(id, reviewRequest);

        ResponseSuccess<ReviewResponse> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Updated review successful. ");
        responseSuccess.setData(reviewResponse);

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById (@PathVariable Long id){
        reviewService.deleteReviewById(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(true);
        messageResponse.setMessage("Deleted review with id: " + id);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
