package com.kits.travel_planner_be.payload.request;

import com.kits.travel_planner_be.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {
    @NotNull
    @Pattern(regexp = AppConstants.DATE_PATTERN, message = "Invalid date format. The expected format is yyyy-MM-dd.")
    private String startDate;

    @NotBlank
    private String departure;

    @NotBlank
    private String arrival;

    @NotBlank
    private String transit;

    @NotBlank
    private String airline;

    @NotBlank
    private Double cost;

    @NotNull
    private Long tripId;
}
