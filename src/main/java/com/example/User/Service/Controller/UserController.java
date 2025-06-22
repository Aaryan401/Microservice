package com.example.User.Service.Controller;

import com.example.User.Service.Entity.Profile;
import com.example.User.Service.Entity.User;
import com.example.User.Service.Model.UserDto;
import com.example.User.Service.Service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-service/v1")
@RequiredArgsConstructor
@Tag(
        name = "User Controller",
        description = "This service handles user registration, profile creation, and user retrieval."
)
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    @Operation(
        summary = "Register a new user",
        description = "This endpoint allows for the registration of a new user by providing user details."
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK: User successfully registered"
    )
    @PostMapping("register")
    public ResponseEntity<String> registerUser(@Parameter(description = "Enter User details", required = true)
                                               @RequestBody User user){
        String response = userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Create a user profile",
        description = "This endpoint allows for the creation of a user profile associated with a specific user ID."
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK: Profile successfully created"
    )
    @PostMapping("create-profile/{userId}")
    public ResponseEntity<String> createProfile(@Parameter(description = "Enter Profile details",required = true)
                                                @RequestBody Profile profile,
                                                @Parameter(description = "Enter UserId", required = true)
                                                @PathVariable("userId") Long userId) {
        String response = userService.saveProfile(profile, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get user by ID",
        description = "This endpoint retrieves user details by their unique user ID."
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK: Give user details successfully"
    )
    @GetMapping("get-user/{userId}")
    public ResponseEntity<UserDto> getUserById(@Parameter(description = "Enter UserId",required = true)
                                               @PathVariable("userId") Long userId) {
        UserDto userDto = userService.findUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
