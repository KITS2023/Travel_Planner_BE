package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;
import com.kits.travel_planner_be.repository.AccommodationRepository;
import com.kits.travel_planner_be.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    @Autowired
    private AccommodationRepository accommodationRepository;

    @Override
    public Accommodation saveAccommodation(AccommodationRequest accommodationRequest) {
        Accommodation accommodation = new Accommodation();
        accommodation.setName(accommodationRequest.getName());
        accommodation.setType(accommodationRequest.getType());
        accommodation.setCheckIn(accommodationRequest.getCheckIn());
        accommodation.setCheckOut(accommodationRequest.getCheckOut());
        accommodation.setAddress(accommodationRequest.getAddress());
        accommodation.setCost(accommodationRequest.getCost());
        accommodation.setStatus(true);

        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation updateAccommodation(Long id, AccommodationRequest accommodationRequest) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", String.valueOf(id)));
        accommodation.setName(accommodationRequest.getName());
        accommodation.setType(accommodationRequest.getType());
        accommodation.setCheckIn(accommodationRequest.getCheckIn());
        accommodation.setCheckOut(accommodationRequest.getCheckOut());
        accommodation.setAddress(accommodationRequest.getAddress());
        accommodation.setCost(accommodationRequest.getCost());
        accommodation.setStatus(true);

        return accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }
}
