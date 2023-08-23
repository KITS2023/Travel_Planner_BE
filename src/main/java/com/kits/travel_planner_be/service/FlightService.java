package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.payload.request.FlightRequest;
import com.kits.travel_planner_be.payload.response.FlightResponse;

import java.util.List;

public interface FlightService {
    List<FlightResponse> getAllFlights();
    List<FlightResponse> getAllFlightsByTrip(Long tripId);
    FlightResponse getFlightById(Long id);
    FlightResponse saveFlight(FlightRequest flightRequest);
    FlightResponse updateFlight(Long id, FlightRequest flightRequest);
    void deleteFlightById(Long id);
}
