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

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsByOrganizer(UserProfile organizer) {
        return eventRepository.findAllByOrganizer(organizer);
    }

    public void registerUserForEvent(UserProfile user, Event event) {
        event.getRegisteredUsers().add(user);
        eventRepository.save(event);
    }

    public void unregisterUserFromEvent(UserProfile user, Event event) {
        event.getRegisteredUsers().remove(user);
        eventRepository.save(event);
    }
}

