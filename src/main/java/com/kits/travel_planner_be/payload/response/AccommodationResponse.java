package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationResponse {
    private Long id;
    private String name;
    private String type;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String address;
    private Double cost;
    private Long tripId;
}
