package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.TripRequest;
import com.kits.travel_planner_be.payload.response.TripDetailResponse;
import com.kits.travel_planner_be.payload.response.TripResponse;

import java.util.List;

public interface TripService {

    List<TripResponse> getAllTripsByUser(Long userId);

    TripDetailResponse getTripById(Long id);

    List<TripResponse> getAllTripsIsPublic();

    TripResponse saveTrip(TripRequest tripRequest);

    TripResponse updateTrip(Long id, TripRequest tripRequest);

    void deleteTripById(Long id);
}
