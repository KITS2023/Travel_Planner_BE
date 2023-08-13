package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSuccess<T> {
    private boolean success = true;
    private String message;
    private T data;
}
