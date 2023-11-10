package com.example.eventcontroller.events.service;

import com.example.eventcontroller.events.models.Event;
import com.example.eventcontroller.events.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Data
public class EventService {
    @Autowired
    private EventRepository eventRepository;


    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEvent(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        // Добавьте обработку случая, если мероприятие не найдено
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        // Обновление данных мероприятия
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        // Обновите другие поля, если необходимо

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long eventId) {
        // Добавьте обработку случая, если мероприятие не найдено
        Event eventToDelete = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        eventRepository.delete(eventToDelete);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
