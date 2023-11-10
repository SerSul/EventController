package com.example.eventcontroller.events.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events/visitor")
@SecurityRequirement(name = "JWT")
public class VisitorController {
    @PostMapping("/{eventId}/registerToEvent")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId) {
        return null;
    }

    @PostMapping("/{eventId}/unregisterFromEvent")
    public ResponseEntity<?> unregisterForEvent(@PathVariable Long eventId) {
        return null;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserEvents(@PathVariable Long userId) {
        return null;
    }
}
