package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.SharedTripRequest;
import com.kits.travel_planner_be.payload.response.SharedTripResponse;
import com.kits.travel_planner_be.payload.response.TripResponse;

import java.util.List;

public interface SharedTripService {
    List<TripResponse> getTripsThatUserIsShared(Long userId);

    SharedTripResponse saveSharedTrip(SharedTripRequest sharedTripRequest);
    SharedTripResponse updateSharedTrip(Long id, SharedTripRequest sharedTripRequest);
    void deleteSharedTripById(Long id);
}
