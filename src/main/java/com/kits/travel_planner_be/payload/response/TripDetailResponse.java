package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDetailResponse {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private DestinationResponse destination;
    private Boolean isPublic;
    private Long userId;
    private List<FlightResponse> flightResponseList;
    private List<AccommodationResponse> accommodationResponseList;
    private List<ActivityResponse> activityResponseList;
}
