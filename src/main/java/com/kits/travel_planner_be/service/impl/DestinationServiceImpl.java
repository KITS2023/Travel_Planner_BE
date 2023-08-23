package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Destination;
import com.kits.travel_planner_be.payload.request.DestinationRequest;
import com.kits.travel_planner_be.payload.response.DestinationResponse;
import com.kits.travel_planner_be.repository.DestinationRepository;
import com.kits.travel_planner_be.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<DestinationResponse> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        List<DestinationResponse> destinationResponses = new ArrayList<>();
        for (Destination destination : destinations) {
            DestinationResponse destinationResponse = new DestinationResponse(destination.getId(),
                    destination.getName(), destination.getDescription(), destination.getRate(), destination.getImageUrl());

            destinationResponses.add(destinationResponse);
        }

        return destinationResponses;
    }

    @Override
    public DestinationResponse getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination", "id", String.valueOf(id)));

        return new DestinationResponse(destination.getId(),
                destination.getName(), destination.getDescription(), destination.getRate(), destination.getImageUrl());
    }

    @Override
    public DestinationResponse saveDestination(DestinationRequest destinationRequest) {
        Destination destination = new Destination();
        destination.setName(destinationRequest.getName());
        destination.setDescription(destinationRequest.getDescription());
        destination.setRate(destinationRequest.getRate());
        destination.setImageUrl(destinationRequest.getImageUrl());

        destinationRepository.save(destination);

        return new DestinationResponse(destination.getId(),
                destination.getName(), destination.getDescription(), destination.getRate(), destination.getImageUrl());
    }

    @Override
    public DestinationResponse updateDestination(Long id, DestinationRequest destinationRequest) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination", "id", String.valueOf(id)));

        destination.setName(destinationRequest.getName());
        destination.setDescription(destinationRequest.getDescription());
        destination.setRate(destinationRequest.getRate());
        destination.setImageUrl(destinationRequest.getImageUrl());

        destinationRepository.save(destination);

        return new DestinationResponse(destination.getId(),
                destination.getName(), destination.getDescription(), destination.getRate(), destination.getImageUrl());
    }

    @Override
    public void deleteDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination", "id", String.valueOf(id)));
        destinationRepository.delete(destination);
    }
}
