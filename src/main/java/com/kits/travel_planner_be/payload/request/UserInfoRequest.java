package com.kits.travel_planner_be.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    private String profilePicture;
    private String preferences;
    @NotBlank
    private String password;
}
