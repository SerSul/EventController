package com.example.eventcontroller.events;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {



    // Получение списка всех мероприятий
    @GetMapping
    public ResponseEntity<?> getAllEvents() {

        return null;
    }


}