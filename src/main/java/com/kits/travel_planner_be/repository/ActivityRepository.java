package com.kits.travel_planner_be.repository;

import com.kits.travel_planner_be.model.Activity;
import com.kits.travel_planner_be.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findActivitiesByTrip(Trip trip);
}
