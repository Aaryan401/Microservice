package com.example.User.Service.Service;

import com.example.User.Service.Entity.Profile;
import com.example.User.Service.Entity.User;
import com.example.User.Service.Model.UserDto;
import com.example.User.Service.Repository.ProfileRepository;
import com.example.User.Service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ProfileRepository profileRepository;

    @Override
    public String saveUser(User user) {
        userRepository.save(user);
        return "User has been registered";
    }

    @Override
    public String saveProfile(Profile profile, Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(foundUser);
        profileRepository.save(profile);
        return "Profile has been created";
    }

    @Override
    public UserDto findUserById(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Profile profile = profileRepository.findByUserUserId(userId).orElseThrow(() -> new RuntimeException("Profile not found for user"));
        UserDto userDto = UserDto.builder()
                .userId(foundUser.getUserId())
                .firstName(foundUser.getFirstName())
                .lastName(foundUser.getLastName())
                .email(foundUser.getEmail())
                .mobile(profile.getMobile())
                .address(profile.getAddress())
                .city(profile.getCity())
                .state(profile.getState())
                .pinCode(profile.getPinCode())
                .build();

        return userDto;
    }


}
