package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Accommodation;
import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.AccommodationRequest;
import com.kits.travel_planner_be.payload.response.AccommodationResponse;
import com.kits.travel_planner_be.repository.AccommodationRepository;
import com.kits.travel_planner_be.repository.TripRepository;
import com.kits.travel_planner_be.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<AccommodationResponse> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        List<AccommodationResponse> accommodationResponses = new ArrayList<>();

        for (Accommodation accommodation : accommodations) {
            AccommodationResponse accommodationResponse = new AccommodationResponse(accommodation.getId(), accommodation.getName(), accommodation.getType(), accommodation.getCheckIn(),
                    accommodation.getCheckOut(), accommodation.getAddress(), accommodation.getCost(), accommodation.getTrip().getId());

            accommodationResponses.add(accommodationResponse);
        }

        return accommodationResponses;
    }

    @Override
    public List<AccommodationResponse> getAllAccommodationsByTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(tripId)));
        List<Accommodation> accommodations = accommodationRepository.findAccommodationsByTrip(trip);
        List<AccommodationResponse> accommodationResponses = new ArrayList<>();

        for (Accommodation accommodation : accommodations) {
            AccommodationResponse accommodationResponse = new AccommodationResponse(accommodation.getId(), accommodation.getName(), accommodation.getType(), accommodation.getCheckIn(),
                    accommodation.getCheckOut(), accommodation.getAddress(), accommodation.getCost(), accommodation.getTrip().getId());

            accommodationResponses.add(accommodationResponse);
        }

        return accommodationResponses;
    }

    @Override
    public AccommodationResponse saveAccommodation(AccommodationRequest accommodationRequest) {
        Accommodation accommodation = new Accommodation();

        accommodation.setName(accommodationRequest.getName());
        accommodation.setType(accommodationRequest.getType());
        accommodation.setCheckIn(LocalDateTime.parse(accommodationRequest.getCheckIn()));
        accommodation.setCheckOut(LocalDateTime.parse(accommodationRequest.getCheckOut()));
        accommodation.setAddress(accommodationRequest.getAddress());
        accommodation.setCost(accommodationRequest.getCost());
        Trip trip = tripRepository.findById(accommodationRequest.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(accommodationRequest.getTripId())));
        accommodation.setTrip(trip);

        accommodationRepository.save(accommodation);

        return new AccommodationResponse(accommodation.getId(), accommodation.getName(), accommodation.getType(), accommodation.getCheckIn(),
                accommodation.getCheckOut(), accommodation.getAddress(), accommodation.getCost(), accommodation.getTrip().getId());
    }

    @Override
    public AccommodationResponse updateAccommodation(Long id, AccommodationRequest accommodationRequest) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", String.valueOf(id)));

        accommodation.setName(accommodationRequest.getName());
        accommodation.setType(accommodationRequest.getType());
        accommodation.setCheckIn(LocalDateTime.parse(accommodationRequest.getCheckIn()));
        accommodation.setCheckOut(LocalDateTime.parse(accommodationRequest.getCheckOut()));
        accommodation.setAddress(accommodationRequest.getAddress());
        accommodation.setCost(accommodationRequest.getCost());

        accommodationRepository.save(accommodation);

        return new AccommodationResponse(accommodation.getId(), accommodation.getName(), accommodation.getType(), accommodation.getCheckIn(),
                accommodation.getCheckOut(), accommodation.getAddress(), accommodation.getCost(), accommodation.getTrip().getId());
    }

    @Override
    public void deleteAccommodationById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", "id", String.valueOf(id)));
        accommodationRepository.delete(accommodation);
    }
}
