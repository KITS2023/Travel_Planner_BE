package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private LocalDate startDate;
    private String departure;
    private String arrival;
    private String transit;
    private String airline;
    private Double cost;
    private Long tripId;
}
