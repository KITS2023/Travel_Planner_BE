package com.kits.travel_planner_be.repository;

import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewsByAccommodation(Accommodation accommodation);
}
