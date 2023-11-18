package com.example.eventcontroller.events.controllers;

import com.example.eventcontroller.auth.security.jwt.JwtUtils;
import com.example.eventcontroller.events.payload.response.MessageResponse;
import com.example.eventcontroller.events.service.EventRegistrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("/registration")
@PreAuthorize("isAuthenticated()")
public class RegistrationController {

    private final EventRegistrationService eventRegistrationService;

    @Autowired
    public RegistrationController(EventRegistrationService eventRegistrationService) {
        this.eventRegistrationService = eventRegistrationService;

    }

    @PostMapping("/registerUserForEvent/{eventId}")
    public ResponseEntity<?> registerUserForEvent(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable Long eventId) {
        try {
           MessageResponse response = eventRegistrationService.registerUserForEvent(authorizationHeader, eventId);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            MessageResponse response = new MessageResponse("error", "Ошибка при регистрации на мероприятие. Попробуйте позже");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/unregisterUserFromEvent/{eventId}")
    public ResponseEntity<?> unregisterUserFromEvent(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable Long eventId) {
        try {
            MessageResponse response = eventRegistrationService.unregisterUserFromEvent(authorizationHeader, eventId);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            MessageResponse response = new MessageResponse("error", "Ошибка при отмене регистрации на мероприятие. Попробуйте позже");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}



