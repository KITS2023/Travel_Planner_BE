package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private Long id;
    private String name;
    private LocalDateTime date;
    private String location;
    private Double cost;
    private Long tripId;
}
