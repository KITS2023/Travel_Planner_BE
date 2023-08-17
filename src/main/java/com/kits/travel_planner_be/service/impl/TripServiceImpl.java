package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.model.User;
import com.kits.travel_planner_be.payload.request.TripRequest;
import com.kits.travel_planner_be.repository.TripRepository;
import com.kits.travel_planner_be.service.TripService;
import com.kits.travel_planner_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Trip> getAllTripsByUser(Long userId) {
        User user = userService.getUserById(userId);
        return tripRepository.findTripsByUser(user);
    }

    @Override
    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
    }

    @Override
    public Trip saveTrip(TripRequest tripRequest) {
        Trip trip = new Trip();
        trip.setTitle(tripRequest.getTitle());
        trip.setStartDate(LocalDate.parse(tripRequest.getStartDate()));
        trip.setEndDate(LocalDate.parse(tripRequest.getEndDate()));
        trip.setDestination(tripRequest.getDestination());
        trip.setIsPublic(false);

        User user = userService.getUserById(tripRequest.getUserId());
        trip.setUser(user);

        return tripRepository.save(trip);
    }

    @Override
    public Trip updateTrip(Long id, TripRequest tripRequest) {
        Trip trip = getTripById(id);

        trip.setTitle(tripRequest.getTitle());
        trip.setStartDate(LocalDate.parse(tripRequest.getStartDate()));
        trip.setEndDate(LocalDate.parse(tripRequest.getEndDate()));
        trip.setDestination(tripRequest.getDestination());
        if(tripRequest.getIsPublic() != null){
            trip.setIsPublic(tripRequest.getIsPublic());
        }

//        User user = userService.getUserById(tripRequest.getUserId());
//        trip.setUser(user);

        return tripRepository.save(trip);
    }

    @Override
    public void deleteTripById(Long id) {
        Trip trip = getTripById(id);
        tripRepository.delete(trip);
    }
}
