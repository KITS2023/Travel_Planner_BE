package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.TripRequest;

import java.util.List;

public interface TripService {

    List<Trip> getAllTripsByUser(Long userId);

    Trip getTripById(Long id);

    Trip saveTrip(TripRequest tripRequest);

    Trip updateTrip(Long id, TripRequest tripRequest);

    void deleteTripById(Long id);
}
