package com.example.eventcontroller.events.service;

import com.example.eventcontroller.auth.security.jwt.JwtUtils;
import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.models.UserProfile;
import com.example.eventcontroller.events.payload.Dtos.EventDto;
import com.example.eventcontroller.events.payload.response.MessageResponse;
import com.example.eventcontroller.events.repository.EventRepository;
import com.example.eventcontroller.events.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final JwtUtils jwtUtils;
    private final UserProfileRepository userProfileRepository;

    public EventService(EventRepository eventRepository, JwtUtils jwtUtils, UserProfileRepository userProfileRepository) {
        this.eventRepository = eventRepository;
        this.jwtUtils = jwtUtils;
        this.userProfileRepository = userProfileRepository;
    }


    public Event createEvent(EventDto eventDto, String authorizationHeader) {
        Long userId = jwtUtils.getUserIdFromJwtToken(authorizationHeader);
        UserProfile existingProfile = userProfileRepository.getUserProfileByUserId(userId);
        Event event= new Event(eventDto.getName(),eventDto.getDate(), eventDto.getLocation(), eventDto.getDescription());
        event.getEvent_organisers().add(existingProfile);
        eventRepository.save(event);
        return event;
    }

    public MessageResponse deleteEvent(Long eventId, String authorizationHeader) {
        Long userId = jwtUtils.getUserIdFromJwtToken(authorizationHeader);
        UserProfile existingProfile = userProfileRepository.getUserProfileByUserId(userId);
        Event existingEvent = eventRepository.findById(eventId).orElse(null);

        if (existingEvent != null) {
            if (existingEvent.getEvent_organisers().contains(existingProfile)) {
                eventRepository.delete(existingEvent);
                return new MessageResponse("success", "Мероприятие удалено");
            } else {
                return new MessageResponse("error", "Мероприятие не найдено");
            }
        }
        return new MessageResponse("error", "Мероприятие не найдено");
    }

    public Event updateEvent(Long eventId, EventDto updatedEventDto) {

        return null;
    }
}
