package com.example.eventcontroller.events.service;

import com.example.eventcontroller.auth.models.User;
import com.example.eventcontroller.auth.repository.UserRepository;
import com.example.eventcontroller.events.models.UserProfile;
import com.example.eventcontroller.events.payload.Dtos.CreateProfileDTO;
import com.example.eventcontroller.events.payload.Dtos.UpdateProfileDTO;
import com.example.eventcontroller.events.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserProfileService {


    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfile createProfile(CreateProfileDTO createProfileDTO, Long userId) {
        if (userProfileRepository.existsByUserId(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Профиль уже существует для данного пользователя");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID: " + userId));

        UserProfile userProfile = new UserProfile(createProfileDTO.getFirstName(), createProfileDTO.getSecondName(), createProfileDTO.getAge(), createProfileDTO.getRole(), user);
        return userProfileRepository.save(userProfile);
    }
    public void updateProfile(Long profileId, Long userId, UpdateProfileDTO updateProfileDTO) {
        UserProfile existingProfile = userProfileRepository.findById(profileId)
                .filter(profile -> profile.getUser().getId().equals(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Профиль не найден с ID: " + profileId));

        if (updateProfileDTO.getFirstName() != null) {
            existingProfile.setFirstName(updateProfileDTO.getFirstName());
        }

        if (updateProfileDTO.getSecondName() != null) {
            existingProfile.setSecondName(updateProfileDTO.getSecondName());
        }

        if (updateProfileDTO.getAge()!=0) {
            existingProfile.setAge(updateProfileDTO.getAge());
        }


        userProfileRepository.save(existingProfile);
    }



    public UserProfile getProfileByUserId(Long id) {
        return userProfileRepository.getUserProfileByUserId(id);
    }

    public void deleteProfile(Long profileId) {
        // Дополнительная логика при удалении профиля
        userProfileRepository.deleteById(profileId);
    }
}

