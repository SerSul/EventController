package com.example.eventcontroller.events;

import com.example.eventcontroller.events.models.*;
import com.example.eventcontroller.events.payload.request.EventDto;
import com.example.eventcontroller.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {
    }

    @PutMapping("/{eventId}/edit")
    public ResponseEntity<?> editEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) {
    }

    @DeleteMapping("/{eventId}/delete")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId) {
    }

    @PostMapping("/{eventId}/unregister")
    public ResponseEntity<?> unregisterForEvent(@PathVariable Long eventId) {
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventDto>> getUserEvents(@PathVariable Long userId) {
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents() {
    }
}
