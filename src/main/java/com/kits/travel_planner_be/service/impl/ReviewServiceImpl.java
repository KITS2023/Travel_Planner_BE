package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.model.Review;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.ReviewRequest;
import com.kits.travel_planner_be.payload.response.ReviewResponse;
import com.kits.travel_planner_be.repository.AccommodationRepository;
import com.kits.travel_planner_be.repository.ReviewRepository;
import com.kits.travel_planner_be.repository.UserRepository;
import com.kits.travel_planner_be.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AccommodationRepository accommodationRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ReviewResponse> getAllReviewsByAccommodation(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "Id", String.valueOf(accommodationId)));
        List<Review> reviews = reviewRepository.getReviewsByAccommodation(accommodation);
        List<ReviewResponse> reviewResponses = new ArrayList<ReviewResponse>();
        for (Review review: reviews){
            reviewResponses.add(new ReviewResponse(review.getRating(),review.getComment(),
                    review.getUser().getId(), review.getAccommodation().getId()));
        }
        return reviewResponses;
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "Id", String.valueOf(id)));
        return new ReviewResponse(review.getRating(),review.getComment(),
                review.getUser().getId(), review.getAccommodation().getId());
    }

    @Override
    public ReviewResponse saveReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());

        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", String.valueOf(reviewRequest.getUserId())));
        Accommodation accommodation = accommodationRepository.findById(reviewRequest.getAccommodationId())
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "Id", String.valueOf(reviewRequest.getAccommodationId())));
        review.setUser(user);
        review.setAccommodation(accommodation);
        reviewRepository.save(review);
        return new ReviewResponse(review.getRating(),review.getComment(),
                review.getUser().getId(), review.getAccommodation().getId());
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "Id", String.valueOf(id)));
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());

        reviewRepository.save(review);
        return new ReviewResponse(review.getRating(),review.getComment(),
                review.getUser().getId(), review.getAccommodation().getId());
    }

    @Override
    public void deleteReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "Id", String.valueOf(id)));
        reviewRepository.delete(review);
    }
}
