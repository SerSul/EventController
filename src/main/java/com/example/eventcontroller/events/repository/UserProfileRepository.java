package com.example.eventcontroller.events.repository;

import com.example.eventcontroller.events.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUserId(Long userId);

    UserProfile getUserProfileByUserId(Long id);
}
