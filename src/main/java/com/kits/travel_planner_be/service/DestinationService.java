package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.payload.request.DestinationRequest;
import com.kits.travel_planner_be.payload.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    List<DestinationResponse> getAllDestinations();

    List<DestinationResponse> getDestinationWithLimit(int limit);

    DestinationResponse getDestinationById(Long id);

    DestinationResponse saveDestination(DestinationRequest destinationRequest);

    DestinationResponse updateDestination(Long id, DestinationRequest destinationRequest);

    void deleteDestinationById(Long id);
}
