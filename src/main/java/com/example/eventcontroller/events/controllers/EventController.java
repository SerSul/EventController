package com.example.eventcontroller.events.controllers;

import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.payload.Dtos.EventDto;
import com.example.eventcontroller.events.payload.response.MessageResponse;
import com.example.eventcontroller.events.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@SecurityRequirement(name = "JWT")
@PreAuthorize("isAuthenticated()")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestBody EventDto eventDto) {
        try {
            Event createdEvent = eventService.createEvent(eventDto, authorizationHeader);
            MessageResponse response = new MessageResponse("success", createdEvent);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            MessageResponse response = new MessageResponse("error", "Ошибка при создании мероприятия. Попробуйте позже");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<?> deleteEvent(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable Long eventId) {

        MessageResponse response = eventService.deleteEvent(eventId, authorizationHeader);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody EventDto updatedEventDto) {
        try {
            Event updatedEvent = eventService.updateEvent(eventId, updatedEventDto);
            return ResponseEntity.ok(updatedEvent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обновлении мероприятия");
        }
    }


    @GetMapping("/getRegisteredUsers")
    public ResponseEntity<?> getApplicants(@RequestHeader(value = "Authorization") String authorizationHeader) {

        return null;
    }
}
