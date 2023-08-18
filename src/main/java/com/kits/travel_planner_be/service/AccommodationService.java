package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;
import com.kits.travel_planner_be.payload.response.AccommodationResponse;


public interface AccommodationService {
    AccommodationResponse saveAccommodation(AccommodationRequest accommodationRequest);
    AccommodationResponse updateAccommodation(Long id, AccommodationRequest accommodationRequest);
    void deleteAccommodationById(Long id);
}
