package com.kits.travel_planner_be.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    @NotBlank
    private int rating;
    @NotBlank
    private String comment;
    @NotNull
    private Long userId;
    @NotNull
    private Long accommodationId;
}
