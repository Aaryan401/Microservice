package com.example.User.Service.Repository;

import com.example.User.Service.Entity.Profile;
import com.example.User.Service.Model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUserUserId(Long userId);
}
