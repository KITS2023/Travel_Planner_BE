package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;
import com.kits.travel_planner_be.payload.response.AccommodationResponse;
import com.kits.travel_planner_be.repository.AccommodationRepository;
import com.kits.travel_planner_be.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    @Autowired
    private AccommodationRepository accommodationRepository;

    @Override
    public AccommodationResponse saveAccommodation(AccommodationRequest accommodationRequest) {
        Accommodation accommodation = new Accommodation();

        accommodation.setName(accommodationRequest.getName());
        accommodation.setType(accommodationRequest.getType());
        accommodation.setCheckIn(accommodationRequest.getCheckIn());
        accommodation.setCheckOut(accommodationRequest.getCheckOut());
        accommodation.setAddress(accommodationRequest.getAddress());
        accommodation.setCost(accommodationRequest.getCost());
        accommodation.setStatus(true);

        accommodationRepository.save(accommodation);

        return new AccommodationResponse(accommodation.getId(), accommodation.getName(), accommodation.getType(), accommodation.getCheckIn(),
                accommodation.getCheckOut(), accommodation.getAddress(), accommodation.getCost(), accommodation.getStatus(), accommodation.getTrip().getId());
    }

    @Override
    public AccommodationResponse updateAccommodation(Long id, AccommodationRequest accommodationRequest) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", String.valueOf(id)));

        accommodation.setName(accommodationRequest.getName());
        accommodation.setType(accommodationRequest.getType());
        accommodation.setCheckIn(accommodationRequest.getCheckIn());
        accommodation.setCheckOut(accommodationRequest.getCheckOut());
        accommodation.setAddress(accommodationRequest.getAddress());
        accommodation.setCost(accommodationRequest.getCost());
        accommodation.setStatus(true);

        accommodationRepository.save(accommodation);

        return new AccommodationResponse(accommodation.getId(), accommodation.getName(), accommodation.getType(), accommodation.getCheckIn(),
                accommodation.getCheckOut(), accommodation.getAddress(), accommodation.getCost(), accommodation.getStatus(), accommodation.getTrip().getId());
    }

    @Override
    public void deleteAccommodationById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", String.valueOf(id)));
        accommodationRepository.delete(accommodation);
    }
}
