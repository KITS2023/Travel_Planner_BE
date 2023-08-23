package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Activity;
import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.ActivityRequest;
import com.kits.travel_planner_be.payload.response.ActivityResponse;
import com.kits.travel_planner_be.repository.ActivityRepository;
import com.kits.travel_planner_be.repository.TripRepository;
import com.kits.travel_planner_be.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<ActivityResponse> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        List<ActivityResponse> activityResponses = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityResponse activityResponse = new ActivityResponse(activity.getId(), activity.getName(), activity.getDate(),
                    activity.getLocation(), activity.getCost(), activity.getTrip().getId());
            activityResponses.add(activityResponse);
        }

        return activityResponses;
    }

    @Override
    public List<ActivityResponse> getAllActivitiesByTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(tripId)));

        List<Activity> activities = activityRepository.findActivitiesByTrip(trip);
        List<ActivityResponse> activityResponses = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityResponse activityResponse = new ActivityResponse(activity.getId(), activity.getName(), activity.getDate(),
                    activity.getLocation(), activity.getCost(), activity.getTrip().getId());
            activityResponses.add(activityResponse);
        }

        return activityResponses;
    }

    @Override
    public ActivityResponse getActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "id", String.valueOf(id)));

        return new ActivityResponse(activity.getId(), activity.getName(), activity.getDate(),
                activity.getLocation(), activity.getCost(), activity.getTrip().getId());
    }

    @Override
    public ActivityResponse saveActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        activity.setName(activityRequest.getName());
        activity.setDate(LocalDateTime.parse(activityRequest.getDate()));
        activity.setLocation(activityRequest.getLocation());
        activity.setCost(activityRequest.getCost());
        Trip trip = tripRepository.findById(activityRequest.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(activityRequest.getTripId())));
        activity.setTrip(trip);

        activityRepository.save(activity);

        return new ActivityResponse(activity.getId(), activity.getName(), activity.getDate(),
                activity.getLocation(), activity.getCost(), activity.getTrip().getId());
    }

    @Override
    public ActivityResponse updateActivity(Long id, ActivityRequest activityRequest) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "id", String.valueOf(id)));

        activity.setName(activityRequest.getName());
        activity.setDate(LocalDateTime.parse(activityRequest.getDate()));
        activity.setLocation(activityRequest.getLocation());
        activity.setCost(activityRequest.getCost());
        Trip trip = tripRepository.findById(activityRequest.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(activityRequest.getTripId())));
        activity.setTrip(trip);

        activityRepository.save(activity);

        return new ActivityResponse(activity.getId(), activity.getName(), activity.getDate(),
                activity.getLocation(), activity.getCost(), activity.getTrip().getId());
    }

    @Override
    public void deleteActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "id", String.valueOf(id)));

        activityRepository.delete(activity);
    }
}
