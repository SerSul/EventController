package com.example.eventcontroller.events.security;

import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class EventSecurityService {

    @Autowired
    private EventRepository eventRepository;

    public boolean isEventBelongToUser(UserDetails userDetails, Long eventId) {
        if (userDetails != null) {
            String username = userDetails.getUsername();

            Event event = eventRepository.findById(eventId).orElse(null);

            return event != null && event.getOrganiser().getUser().getUsername().equals(username);
        }

        return false;
    }
}

