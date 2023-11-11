package com.example.eventcontroller.events.controllers;


import com.example.eventcontroller.auth.repository.UserRepository;
import com.example.eventcontroller.auth.security.jwt.JwtUtils;
import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.models.Organiser;
import com.example.eventcontroller.events.payload.Dtos.CreateEventDTO;

import com.example.eventcontroller.events.payload.response.JsonResponseHandler;
import com.example.eventcontroller.events.repository.EventRepository;
import com.example.eventcontroller.events.service.EventService;
import com.example.eventcontroller.events.service.OrganiserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/organiser")
@SecurityRequirement(name = "JWT")
@PreAuthorize("isAuthenticated()")
public class OrganiserController {



    @Autowired
    private JsonResponseHandler jsonResponseHandler;

    @Autowired
    EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganiserService organiserService;

    @PostMapping("/createEvent")
    @Valid
    @Transactional
    public ResponseEntity<?> createEvent(@RequestBody @Valid CreateEventDTO eventDto,
                                         @RequestHeader(value = "Authorization") String authorizationHeader) {


        Organiser savedOrganiser = organiserService.getOrganiserByUserId(jwtUtils.getUserIdFromJwtToken(authorizationHeader));

        if (savedOrganiser == null) {
            Organiser organiser = new Organiser(userRepository.findUserById(jwtUtils.getUserIdFromJwtToken(authorizationHeader)), eventDto.getOrganiserDTO().getFirst_name(), eventDto.getOrganiserDTO().getLast_name(), eventDto.getOrganiserDTO().getAge());
            savedOrganiser = organiserService.createOrganiser(organiser);
        }

        Event event=new Event(eventDto.getName(),eventDto.getDescription(), eventDto.getCategory(),eventDto.getLocation(),eventDto.getStartTime(),eventDto.getEndTime(), savedOrganiser);
        Event createdEvent = eventService.createEvent(event);

        return ResponseEntity.ok(createdEvent);
    }
    @Valid
    @Transactional
    @PutMapping("/{eventId}/editEvent")
    @PreAuthorize("@eventSecurityService.isEventBelongToUser(authentication.principal, #eventId)")
    public ResponseEntity<?> editEvent(@PathVariable Long eventId, @RequestBody CreateEventDTO eventDto) {
        return ResponseEntity.ok("da");
    }
    @Valid
    @Transactional
    @PreAuthorize("@eventSecurityService.isEventBelongToUser(authentication.principal, #eventId)")
    @DeleteMapping("/{eventId}/deleteEvent")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId, @RequestHeader(value = "Authorization") String authorizationHeader) {
        boolean eventDeleted = eventService.deleteEvent(eventId);

        if (eventDeleted) {
            return jsonResponseHandler.createSuccessResponseWMessage("Событие удалено");
        } else {
            return jsonResponseHandler.createNotFoundResponse("Событие не найдено");
        }
    }


    @GetMapping("/getAllEvents")
    public ResponseEntity<?> getAllEvents(@RequestHeader(value = "Authorization") String authorizationHeader) {
        Long organiserId = organiserService.getOrganiserByUserId(jwtUtils.getUserIdFromJwtToken(authorizationHeader)).getId();
        List<Event> events = eventService.getEventsByOrganiserId(organiserId);

        if (!events.isEmpty()) {
            return jsonResponseHandler.createSuccessResponse(events);
        } else {
            return jsonResponseHandler.createNotFoundResponse("События не найдены");
        }
    }



}

/*
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfaWQiOjIsImlhdCI6MTY5OTYyMzAwMywiZXhwIjoxNjk5NzA5NDAzfQ.MZd9lLwRMYkb1PYuuXHHEFnr3oqXKhVMeLt66wbIoVI
 */