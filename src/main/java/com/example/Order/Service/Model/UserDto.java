package com.example.Order.Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    @Schema(
            description = "Unique identifier for the user",
            example = "1"
    )
    private Long userId;

    @Schema(
            description = "Unique identifier for the user profile",
            example = "1"
    )
    private Long profileId;

    @Schema(
            description = "First name of the user",
            example = "Aaryan"
    )
    private String firstName;

    @Schema(
            description = "Last name of the user",
            example = "Prashar"
    )
    private String lastName;

    @Schema(
            description = "Age of the user",
            example = "25"
    )
    private int age;

    @Schema(
            description = "Email address of the user",
            example = "aaryan9@gmail.com"
    )
    private String email;

    @Schema(
            description = "Mobile number of the user",
            example = "9876543210"
    )
    private String mobile;

    @Schema(
            description = "Address of the user",
            example = "Mahadevapura"
    )
    private String address;

    @Schema(
            description = "City where the user resides",
            example = "Bangalore"
    )
    private String city;

    @Schema(
            description = "State where the user resides",
            example = "Karnataka"
    )
    private String state;

    @Schema(
            description = "Pin code for the user's address",
            example = "560048"
    )
    private String pinCode;
}
