package com.kits.travel_planner_be.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", message = "Invalid date format. The expected format is yyyy-MM-ddTHH:mm:ss.")
    private LocalDateTime checkIn;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", message = "Invalid date format. The expected format is yyyy-MM-ddTHH:mm:ss.")
    private LocalDateTime checkOut;

    @NotBlank
    private String address;

    @NotNull
    private Double cost;

    private boolean status;
}
