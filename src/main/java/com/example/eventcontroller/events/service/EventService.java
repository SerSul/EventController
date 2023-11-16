package com.example.eventcontroller.events.service;

import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.models.UserProfile;
import com.example.eventcontroller.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }





}

