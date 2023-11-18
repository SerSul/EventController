package com.example.eventcontroller.events.service;

import com.example.eventcontroller.auth.models.User;
import com.example.eventcontroller.auth.repository.UserRepository;
import com.example.eventcontroller.events.models.UserProfile;
import com.example.eventcontroller.events.payload.Dtos.ProfileDTO;
import com.example.eventcontroller.events.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class UserProfileService {


    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfile createProfile(ProfileDTO createProfileDTO, Long userId) {
        if (userProfileRepository.existsByUserId(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Профиль уже существует для данного пользователя");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID: " + userId));

        UserProfile userProfile = new UserProfile(createProfileDTO.getFirstName(), createProfileDTO.getSecondName(), createProfileDTO.getAge(), user);
        return userProfileRepository.save(userProfile);
    }
    public void updateProfile(Long userId, ProfileDTO profileDTO) {

        UserProfile existingProfile = userProfileRepository.getUserProfileByUserId(userId);
        if (profileDTO.getFirstName() != null) {
            existingProfile.setFirstName(profileDTO.getFirstName());
        }
        if (profileDTO.getSecondName() != null) {
            existingProfile.setSecondName(profileDTO.getSecondName());
        }
        if (profileDTO.getAge()!=null)
        {
            existingProfile.setAge(profileDTO.getAge());
        }

        userProfileRepository.save(existingProfile);
    }





    public void deleteProfile(Long userId) {
        try {


                userProfileRepository.deleteUserProfileByUser_Id(userId);
                System.out.println("Profile deleted successfully");

        } catch (Exception e) {
            System.out.println("Error deleting profile: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при удалении профиля");
        }
    }


}

