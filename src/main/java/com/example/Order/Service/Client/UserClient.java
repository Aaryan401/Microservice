package com.example.Order.Service.Client;


import com.example.Order.Service.Model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "User-Service")
public interface UserClient {

    @GetMapping("api/user-service/v1/get-user/{userId}")
    public UserDto findUserById(@PathVariable("userId") Long userId);
}
