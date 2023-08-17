package com.kits.travel_planner_be.payload.request;

import java.time.LocalDate;

public class TripRequest {

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String destination;

    private Long userId;
}
