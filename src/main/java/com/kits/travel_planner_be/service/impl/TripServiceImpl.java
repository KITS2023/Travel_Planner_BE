package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.*;
import com.kits.travel_planner_be.payload.request.TripRequest;
import com.kits.travel_planner_be.payload.response.*;
import com.kits.travel_planner_be.repository.*;
import com.kits.travel_planner_be.service.AccommodationService;
import com.kits.travel_planner_be.service.ActivityService;
import com.kits.travel_planner_be.service.FlightService;
import com.kits.travel_planner_be.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private ActivityService activityService;

    @Override
    public List<TripResponse> getAllTripsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(userId)));

        List<Trip> trips = tripRepository.findTripsByUser(user);
        List<TripResponse> tripResponses = new ArrayList<>();
        for (Trip trip : trips) {
            DestinationResponse destinationResponse = new DestinationResponse(trip.getDestination().getId(),
                    trip.getDestination().getName(), trip.getDestination().getDescription(), trip.getDestination().getImageUrl());
            TripResponse tripResponse = new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                    destinationResponse, trip.getIsPublic(), trip.getUser().getId());
            tripResponses.add(tripResponse);
        }

        return tripResponses;
    }

    @Override
    public TripDetailResponse getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));

        DestinationResponse destinationResponse = new DestinationResponse(trip.getDestination().getId(),
                trip.getDestination().getName(), trip.getDestination().getDescription(), trip.getDestination().getImageUrl());

        List<FlightResponse> flightResponses = flightService.getAllFlightsByTrip(id);
        List<AccommodationResponse> accommodationResponses = accommodationService.getAllAccommodationsByTrip(id);
        List<ActivityResponse> activityResponses = activityService.getAllActivitiesByTrip(id);

        return new TripDetailResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                destinationResponse, trip.getIsPublic(), trip.getUser().getId(), flightResponses, accommodationResponses, activityResponses);
    }

    @Override
    public TripResponse saveTrip(TripRequest tripRequest) {
        Trip trip = new Trip();
        trip.setTitle(tripRequest.getTitle());
        trip.setStartDate(LocalDate.parse(tripRequest.getStartDate()));
        trip.setEndDate(LocalDate.parse(tripRequest.getEndDate()));

        Destination destination = destinationRepository.findById(tripRequest.getDestinationId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination", "id", String.valueOf(tripRequest.getDestinationId())));

        trip.setDestination(destination);
        if (tripRequest.getIsPublic() != null) {
            trip.setIsPublic(tripRequest.getIsPublic());
        }

        User user = userRepository.findById(tripRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(tripRequest.getUserId())));
        trip.setUser(user);

        tripRepository.save(trip);

        DestinationResponse destinationResponse = new DestinationResponse(trip.getDestination().getId(),
                trip.getDestination().getName(), trip.getDestination().getDescription(), trip.getDestination().getImageUrl());

        return new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                destinationResponse, trip.getIsPublic(), trip.getUser().getId());
    }

    @Override
    public TripResponse updateTrip(Long id, TripRequest tripRequest) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));

        trip.setTitle(tripRequest.getTitle());
        trip.setStartDate(LocalDate.parse(tripRequest.getStartDate()));
        trip.setEndDate(LocalDate.parse(tripRequest.getEndDate()));
        Destination destination = destinationRepository.findById(tripRequest.getDestinationId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination", "id", String.valueOf(tripRequest.getDestinationId())));
        trip.setDestination(destination);
        if (tripRequest.getIsPublic() != null) {
            trip.setIsPublic(tripRequest.getIsPublic());
        }

        tripRepository.save(trip);

        DestinationResponse destinationResponse = new DestinationResponse(trip.getDestination().getId(),
                trip.getDestination().getName(), trip.getDestination().getDescription(), trip.getDestination().getImageUrl());

        return new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                destinationResponse, trip.getIsPublic(), trip.getUser().getId());
    }

    @Override
    public void deleteTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));

        tripRepository.delete(trip);
    }
}
