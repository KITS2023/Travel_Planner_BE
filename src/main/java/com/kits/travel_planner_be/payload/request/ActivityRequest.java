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
public class ActivityRequest {

    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", message = "Invalid date format. The expected format is yyyy-MM-ddTHH:mm:ss.")
    private String date;

    @NotBlank
    private String location;

    private Double cost;

    @NotNull
    private Long tripId;
}
