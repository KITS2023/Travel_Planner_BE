package com.kits.travel_planner_be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String profilePicture;
    private String preferences;
    private String role;
}
