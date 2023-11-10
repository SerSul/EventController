package com.example.eventcontroller.events.repository;

import com.example.eventcontroller.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByOrganiserId(Long id);

    Boolean existsByIdAndOrganiserId(Long eventId, Long id);
    void deleteEventByIdAndOrganiserId(Long eventId, Long id);

}
