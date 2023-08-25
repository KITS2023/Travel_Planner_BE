package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedTripResponse {
    private Long tripId;
    private Long sharedUserId;
    private LocalDateTime sharedDate;
}
