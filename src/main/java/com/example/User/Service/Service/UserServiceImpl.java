package com.example.User.Service.Service;

import com.example.User.Service.Entity.User;
import com.example.User.Service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public String saveUser(User user) {
        userRepository.save(user);
        return "User has been registered";
    }
}
