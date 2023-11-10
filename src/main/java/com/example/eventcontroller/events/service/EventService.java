package com.example.eventcontroller.events.service;

import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Data
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());

        return eventRepository.save(existingEvent);
    }

    public String deleteEvent(Long eventId, Long organiserId) {
        boolean eventExists = eventRepository.existsByIdAndOrganiserId(eventId, organiserId);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();

        if (eventExists) {
            eventRepository.deleteEventByIdAndOrganiserId(eventId, organiserId);
            response.put("message", "Событие удалено");
        } else {
            response.put("message", "Событие не найдено");
        }

        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"message\": \"Ошибка обработки данных\"}";
        }
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventByOrganiserId(Long id) {
        return eventRepository.getEventsByOrganiserId(id);
    }
}
