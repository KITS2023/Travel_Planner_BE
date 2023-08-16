package com.kits.travel_planner_be.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotNull
    private Timestamp checkIn;
    @NotNull
    private Timestamp checkOut;
    @NotBlank
    private String address;
    @NotNull
    private Double cost;
    private boolean status;
}
