package com.example.eventcontroller.events.service;

import com.example.eventcontroller.auth.security.jwt.JwtUtils;
import com.example.eventcontroller.events.models.Event;

import com.example.eventcontroller.events.models.UserProfile;
import com.example.eventcontroller.events.payload.response.MessageResponse;
import com.example.eventcontroller.events.repository.EventRepository;
import com.example.eventcontroller.events.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRegistrationService {
    private final EventRepository eventRepository;
    private final UserProfileRepository userProfileRepository;
    private final JwtUtils jwtUtils;


    @Autowired
    public EventRegistrationService(EventRepository eventRepository, UserProfileRepository userProfileRepository, JwtUtils jwtUtils) {
        this.eventRepository = eventRepository;
        this.userProfileRepository = userProfileRepository;

        this.jwtUtils = jwtUtils;
    }

    public MessageResponse registerUserForEvent(String authorizationHeader, Long eventId) {
        Long userId = jwtUtils.getUserIdFromJwtToken(authorizationHeader);


        UserProfile existingProfile = userProfileRepository.getUserProfileByUserId(userId);
        if (existingProfile == null) {
            return new MessageResponse("error", "Пользователь не найден");
        }

        Event event = eventRepository.getEventById(eventId);


        if (event == null) {
            return new MessageResponse("error", "Событие не найдено");
        }


        if (existingProfile.getEvents().contains(event)) {
            return new MessageResponse("error", "Пользователь уже зарегестрирован");
        }


        existingProfile.getEvents().add(event);
        userProfileRepository.save(existingProfile);
        return new MessageResponse("success", "Пользователь успешно зарегестрирован");
    }


    public MessageResponse unregisterUserFromEvent(String authorizationHeader, Long eventId) {
        Long userId = jwtUtils.getUserIdFromJwtToken(authorizationHeader);
        UserProfile existingProfile = userProfileRepository.getUserProfileByUserId(userId);
        Event event = eventRepository.getEventById(eventId);


        if (existingProfile == null || event == null) {
            return new MessageResponse("error", "Профиль пользователя или событие не найдены");
        }


        if (!existingProfile.getEvents().contains(event)) {
            return new MessageResponse("error", "Пользователь не был зарегистрирован на это событие");
        }

        existingProfile.getEvents().remove(event);
        userProfileRepository.save(existingProfile);

        return new MessageResponse("success", "Регистрация на мероприятие отменена успешно");
    }


}

