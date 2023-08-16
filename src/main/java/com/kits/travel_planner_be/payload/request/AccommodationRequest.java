package com.kits.travel_planner_be.payload.request;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private Timestamp checkIn;
    @NotBlank
    private Timestamp checkOut;
    @NotBlank
    private String address;
    @NotBlank
    private double cost;
    @NotBlank
    private boolean status;
}
