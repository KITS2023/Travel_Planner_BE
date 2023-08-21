package com.kits.travel_planner_be.payload.request;

import com.kits.travel_planner_be.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedTripRequest {
    @NotNull
    private Long tripId;

    @NotNull
    private Long sharedUserId;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", message = "Invalid date format. The expected format is yyyy-MM-ddTHH:mm:ss.")
    private String sharedDate;
}
