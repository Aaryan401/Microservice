package com.example.User.Service.Controller;

import com.example.User.Service.Entity.User;
import com.example.User.Service.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
