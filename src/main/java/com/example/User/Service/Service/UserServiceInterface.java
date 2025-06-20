package com.example.User.Service.Service;

import com.example.User.Service.Entity.Profile;
import com.example.User.Service.Entity.User;
import com.example.User.Service.Model.UserDto;

public interface UserServiceInterface {
    public String saveUser(User user);
    public String saveProfile(Profile profile, Long userId);
    public UserDto findUserById(Long userId);
}
