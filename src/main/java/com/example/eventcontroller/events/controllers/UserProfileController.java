package com.example.eventcontroller.events.controllers;


import com.example.eventcontroller.auth.security.jwt.JwtUtils;
import com.example.eventcontroller.events.models.UserProfile;
import com.example.eventcontroller.events.payload.Dtos.CreateProfileDTO;
import com.example.eventcontroller.events.payload.Dtos.UpdateProfileDTO;
import com.example.eventcontroller.events.payload.response.MessageResponse;

import com.example.eventcontroller.events.service.UserProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, JwtUtils jwtUtils) {
        this.userProfileService = userProfileService;

        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/createProfile")
    public ResponseEntity<?> createProfile(@RequestBody CreateProfileDTO createProfileDTO, @RequestHeader(value = "Authorization") String authorizationHeader) {
        try {
            Long userId = jwtUtils.getUserIdFromJwtToken(authorizationHeader);
            UserProfile userProfile = userProfileService.createProfile(createProfileDTO, userId);

            MessageResponse response = new MessageResponse("success", "Профиль успешно создан", userProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResponseStatusException e) {
            MessageResponse response = new MessageResponse("error", "Ошибка при создании профиля: Профиль уже существует для данного пользователя");
            return ResponseEntity.status(e.getStatusCode()).body(response);
        }
    }




    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO, @RequestHeader(value = "Authorization") String authorizationHeader) {
        try {
            Long userId = jwtUtils.getUserIdFromJwtToken(authorizationHeader);

            userProfileService.updateProfile(userId, updateProfileDTO);

            MessageResponse response = new MessageResponse("success", "Профиль успешно обновлен");
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            MessageResponse response = new MessageResponse("error", "Ошибка при обновлении профиля: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
