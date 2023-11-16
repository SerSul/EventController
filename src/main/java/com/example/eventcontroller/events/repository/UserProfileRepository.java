package com.example.eventcontroller.events.repository;

import com.example.eventcontroller.events.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUserId(Long userId);

    void deleteUserProfileByUser_Id(Long id);
    UserProfile getUserProfileByUserId(Long id);
}
