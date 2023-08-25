package com.kits.travel_planner_be.payload.response;

import com.kits.travel_planner_be.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private DestinationResponse destination;
    private Boolean isPublic;
    private User user;
}
