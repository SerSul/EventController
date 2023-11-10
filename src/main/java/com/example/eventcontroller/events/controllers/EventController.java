package com.example.eventcontroller.events.controllers;

import com.example.eventcontroller.events.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@SecurityRequirement(name = "JWT")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents() {
        return null;
    }
}
