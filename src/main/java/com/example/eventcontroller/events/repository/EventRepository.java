package com.example.eventcontroller.events.repository;

import com.example.eventcontroller.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event getEventById(Long eventId);
}

