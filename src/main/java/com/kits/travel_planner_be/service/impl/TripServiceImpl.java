package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.TripRequest;
import com.kits.travel_planner_be.payload.response.TripResponse;
import com.kits.travel_planner_be.repository.TripRepository;
import com.kits.travel_planner_be.repository.UserRepository;
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

    @Override
    public List<TripResponse> getAllTripsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(userId)));

        List<Trip> trips = tripRepository.findTripsByUser(user);
        List<TripResponse> tripResponses = new ArrayList<>();
        for (Trip trip : trips) {
            TripResponse tripResponse = new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                    trip.getDestination(), trip.getIsPublic(), trip.getUser().getId());
            tripResponses.add(tripResponse);
        }

        return tripResponses;
    }

    @Override
    public TripResponse getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));

        return new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                trip.getDestination(), trip.getIsPublic(), trip.getUser().getId());
    }

    @Override
    public TripResponse saveTrip(TripRequest tripRequest) {
        Trip trip = new Trip();
        trip.setTitle(tripRequest.getTitle());
        trip.setStartDate(LocalDate.parse(tripRequest.getStartDate()));
        trip.setEndDate(LocalDate.parse(tripRequest.getEndDate()));
        trip.setDestination(tripRequest.getDestination());
        trip.setIsPublic(false);

        User user = userRepository.findById(tripRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(tripRequest.getUserId())));
        trip.setUser(user);

        tripRepository.save(trip);

        return new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                trip.getDestination(), trip.getIsPublic(), trip.getUser().getId());
    }

    @Override
    public TripResponse updateTrip(Long id, TripRequest tripRequest) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));

        trip.setTitle(tripRequest.getTitle());
        trip.setStartDate(LocalDate.parse(tripRequest.getStartDate()));
        trip.setEndDate(LocalDate.parse(tripRequest.getEndDate()));
        trip.setDestination(tripRequest.getDestination());
        if (tripRequest.getIsPublic() != null) {
            trip.setIsPublic(tripRequest.getIsPublic());
        }

//        User user = userService.getUserById(tripRequest.getUserId());
//        trip.setUser(user);
        tripRepository.save(trip);

        return new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                trip.getDestination(), trip.getIsPublic(), trip.getUser().getId());
    }

    @Override
    public void deleteTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));

        tripRepository.delete(trip);
    }
}
