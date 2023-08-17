package com.kits.travel_planner_be.payload.request;

import com.kits.travel_planner_be.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {

    @NotBlank
    private String title;

    @NotNull
    @Pattern(regexp = AppConstants.DATE_PATTERN, message = "Invalid date format. The expected format is yyyy-MM-dd.")
    private String startDate;

    @NotNull
    @Pattern(regexp = AppConstants.DATE_PATTERN, message = "Invalid date format. The expected format is yyyy-MM-dd.")
    private String endDate;

    @NotBlank
    private String destination;

    @NotNull
    private Long userId;

    private Boolean isPublic;
}
