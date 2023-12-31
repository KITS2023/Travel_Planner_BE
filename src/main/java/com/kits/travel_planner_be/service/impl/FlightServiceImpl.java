package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.model.Flight;
import com.kits.travel_planner_be.model.Trip;
import com.kits.travel_planner_be.payload.request.FlightRequest;
import com.kits.travel_planner_be.payload.response.FlightResponse;
import com.kits.travel_planner_be.repository.FlightRepository;
import com.kits.travel_planner_be.repository.TripRepository;
import com.kits.travel_planner_be.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();

        List<FlightResponse> flightResponses = new ArrayList<FlightResponse>();
        for (Flight flight : flights) {
            flightResponses.add(new FlightResponse(flight.getStartDate(), flight.getDeparture(), flight.getArrival(),
                    flight.getTransit(), flight.getAirline(), flight.getCost(), flight.getTrip().getId()));
        }
        return flightResponses;
    }

    @Override
    public List<FlightResponse> getAllFlightsByTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", String.valueOf(tripId)));
        List<Flight> flights = flightRepository.getFlightsByTrip(trip);
        List<FlightResponse> flightResponses = new ArrayList<FlightResponse>();
        for (Flight flight : flights) {
            flightResponses.add(new FlightResponse(flight.getStartDate(), flight.getDeparture(), flight.getArrival(),
                    flight.getTransit(), flight.getAirline(), flight.getCost(), flight.getTrip().getId()));
        }
        return flightResponses;
    }

    @Override
    public FlightResponse getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "Id", String.valueOf(id)));
        return new FlightResponse(flight.getStartDate(), flight.getDeparture(), flight.getArrival(), flight.getTransit(),
                flight.getAirline(), flight.getCost(), flight.getTrip().getId());
    }

    @Override
    public FlightResponse saveFlight(FlightRequest flightRequest) {
        Flight flight = new Flight();
        flight.setStartDate(LocalDate.parse(flightRequest.getStartDate()));
        flight.setDeparture(flightRequest.getDeparture());
        flight.setArrival(flightRequest.getArrival());
        flight.setTransit(flightRequest.getTransit());
        flight.setAirline(flightRequest.getAirline());

        Trip trip = tripRepository.findById(flightRequest.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", String.valueOf(flightRequest.getTripId())));
        flight.setTrip(trip);
        flight.setCost(flightRequest.getCost());

        flightRepository.save(flight);
        return new FlightResponse(flight.getStartDate(), flight.getDeparture(), flight.getArrival(),
                flight.getTransit(), flight.getAirline(), flight.getCost(), flight.getTrip().getId());
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "Id", String.valueOf(id)));

        flight.setStartDate(LocalDate.parse(flightRequest.getStartDate()));
        flight.setDeparture(flightRequest.getDeparture());
        flight.setArrival(flightRequest.getArrival());
        flight.setTransit(flightRequest.getTransit());
        flight.setAirline(flightRequest.getAirline());
        flight.setCost(flightRequest.getCost());

        flightRepository.save(flight);
        return new FlightResponse(flight.getStartDate(), flight.getDeparture(), flight.getArrival(),
                flight.getTransit(), flight.getAirline(), flight.getCost(), flight.getTrip().getId());
    }

    @Override
    public void deleteFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "Id", String.valueOf(id)));
        flightRepository.delete(flight);
    }
}
