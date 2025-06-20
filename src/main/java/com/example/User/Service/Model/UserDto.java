package com.example.User.Service.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long userId;

    private Long profileId;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String mobile;

    private String address;

    private String city;

    private String state;

    private String pinCode;
}
