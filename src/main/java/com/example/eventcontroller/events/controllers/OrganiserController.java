package com.example.eventcontroller.events.controllers;


import com.example.eventcontroller.auth.repository.UserRepository;
import com.example.eventcontroller.auth.security.jwt.JwtUtils;
import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.models.Organiser;
import com.example.eventcontroller.events.payload.Dtos.CreateEventDTO;

import com.example.eventcontroller.events.service.EventService;
import com.example.eventcontroller.events.service.OrganiserService;
import io.jsonwebtoken.Jwt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events/organiser")
@SecurityRequirement(name = "JWT")
@PreAuthorize("isAuthenticated()")
public class OrganiserController {

    @Autowired
    private EventService eventService;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganiserService organiserService;

    @PostMapping("/createEvent") @Valid
    public ResponseEntity<?> createEvent(@RequestBody @Valid CreateEventDTO eventDto,
                                         @RequestHeader(value = "Authorization") String authorizationHeader) {

        Organiser organiser = new Organiser(userRepository.findUserById(jwtUtils.getUserIdFromJwtToken(authorizationHeader)), eventDto.getOrganiserDTO().getFirst_name(),eventDto.getOrganiserDTO().getLast_name(),eventDto.getOrganiserDTO().getAge());
        Organiser savedOrganiser = organiserService.createOrganiser(organiser);

        Event event=new Event(eventDto.getName(),eventDto.getDescription(), eventDto.getCategory(),eventDto.getLocation(),eventDto.getStartTime(),eventDto.getEndTime(), savedOrganiser);
        Event createdEvent = eventService.createEvent(event);

        return ResponseEntity.ok(createdEvent);
    }

    @PutMapping("/{eventId}/editEvent")
    public ResponseEntity<?> editEvent(@PathVariable Long eventId, @RequestBody CreateEventDTO eventDto) {
        return null;
    }

    @DeleteMapping("/{eventId}/deleteEvent")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        return null;
    }

    @GetMapping("/getAllEvents")
    public  ResponseEntity<?> getAllEvents(@RequestHeader(value = "Authorization") String authorizationHeader)
    {

        return null;
    }

}
