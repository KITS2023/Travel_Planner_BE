package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.payload.request.ReviewRequest;
import com.kits.travel_planner_be.payload.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getAllReviewsByAccommodation(Long accommodationId);
    ReviewResponse getReviewById(Long id);
    ReviewResponse saveReview(ReviewRequest reviewRequest);
    ReviewResponse updateReview(Long id, ReviewRequest reviewRequest);
    void deleteReviewById(Long id);
}
