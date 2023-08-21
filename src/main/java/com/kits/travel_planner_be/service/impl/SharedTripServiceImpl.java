package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.SharedTrip;
import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.SharedTripRequest;
import com.kits.travel_planner_be.payload.response.SharedTripResponse;
import com.kits.travel_planner_be.payload.response.TripResponse;
import com.kits.travel_planner_be.repository.SharedTripRepository;
import com.kits.travel_planner_be.repository.TripRepository;
import com.kits.travel_planner_be.repository.UserRepository;
import com.kits.travel_planner_be.service.SharedTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SharedTripServiceImpl implements SharedTripService {
    @Autowired
    private SharedTripRepository sharedTripRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TripResponse> getTripsThatUserIsShared(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", String.valueOf(userId)));
        List<Trip> trips = sharedTripRepository.getTripsThatUserIsShared(user.getId());
        List<TripResponse> tripResponses = new ArrayList<>();
        for (Trip trip : trips){
            TripResponse tripResponse = new TripResponse(trip.getId(), trip.getTitle(), trip.getStartDate(), trip.getEndDate(),
                    trip.getDestination(), trip.getIsPublic(), trip.getUser().getId());
            tripResponses.add(tripResponse);
        }

        return tripResponses;
    }

    @Override
    public SharedTripResponse saveSharedTrip(SharedTripRequest sharedTripRequest) {
        SharedTrip sharedTrip = new SharedTrip();

        Trip trip = tripRepository.findById(sharedTripRequest.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", String.valueOf(sharedTripRequest.getTripId())));
        User user = userRepository.findById(sharedTripRequest.getSharedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", String.valueOf(sharedTripRequest.getSharedUserId())));
        sharedTrip.setTrip(trip);
        sharedTrip.setUser(user);
        sharedTrip.setSharedDate(LocalDateTime.parse(sharedTripRequest.getSharedDate()));

        sharedTripRepository.save(sharedTrip);
        return new SharedTripResponse(sharedTrip.getTrip().getId(), sharedTrip.getUser().getId(), sharedTrip.getSharedDate() );
    }

    @Override
    public SharedTripResponse updateSharedTrip(Long id, SharedTripRequest sharedTripRequest) {
        SharedTrip sharedTrip = sharedTripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SharedTrip","Id", String.valueOf(id)));
        User user = userRepository.findById(sharedTripRequest.getSharedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", String.valueOf(sharedTripRequest.getSharedUserId())));
        sharedTrip.setUser(user);
        sharedTripRepository.save(sharedTrip);
        return new SharedTripResponse(sharedTrip.getTrip().getId(), sharedTrip.getUser().getId(), sharedTrip.getSharedDate());
    }

    @Override
    public void deleteSharedTripById(Long id) {
        SharedTrip sharedTrip = sharedTripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SharedTrip", "Id", String.valueOf(id)));
        sharedTripRepository.delete(sharedTrip);
    }
}
