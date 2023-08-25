package com.kits.travel_planner_be.repository;

import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findTripsByUser(User user);
    List<Trip> findTripsByIsPublic(Boolean isPublic);
}
