package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.Activity;
import com.kits.travel_planner_be.payload.request.ActivityRequest;
import com.kits.travel_planner_be.payload.response.ActivityResponse;

import java.util.List;

public interface ActivityService {
    List<ActivityResponse> getAllActivitiesByTrip(Long tripId);

    ActivityResponse getActivityById(Long id);

    ActivityResponse saveActivity(ActivityRequest activityRequest);

    ActivityResponse updateActivity(Long id, ActivityRequest activityRequest);

    void deleteActivityById(Long id);


}
