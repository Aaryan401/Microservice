package com.example.User.Service.Controller;

import com.example.User.Service.Entity.Profile;
import com.example.User.Service.Entity.User;
import com.example.User.Service.Model.UserDto;
import com.example.User.Service.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-service/v1")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        String response = userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("create-profile/{userId}")
    public ResponseEntity<String> createProfile(@RequestBody Profile profile, Long userId) {
        String response = userService.saveProfile(profile, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto userDto = userService.findUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
