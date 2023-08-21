package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;
import com.kits.travel_planner_be.payload.response.AccommodationResponse;
import com.kits.travel_planner_be.payload.response.ActivityResponse;

import java.util.List;


public interface AccommodationService {

    List<AccommodationResponse> getAllAccommodationsByTrip(Long tripId);
    AccommodationResponse saveAccommodation(AccommodationRequest accommodationRequest);
    AccommodationResponse updateAccommodation(Long id, AccommodationRequest accommodationRequest);
    void deleteAccommodationById(Long id);
}
