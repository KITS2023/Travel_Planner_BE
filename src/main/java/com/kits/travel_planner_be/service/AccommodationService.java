package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;


public interface AccommodationService {
    Accommodation saveAccommodation(AccommodationRequest accommodationRequest);
    Accommodation updateAccommodation(Long id, AccommodationRequest accommodationRequest);
    void deleteAccommodation(Long id);
}
