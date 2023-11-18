package com.example.eventcontroller.events.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime date;
    private String location;
    private String description;
    private boolean registrationOpen;

    public Event(String name, LocalDateTime date, String location, String description) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
        this.registrationOpen = true;
    }


    @JoinTable(
            name = "events_organisers",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_profile_id")
    )
    @ManyToMany
    private Set<UserProfile> event_organisers = new HashSet<>();

    @JoinTable(
            name = "registered_to_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_profile_id")
    )
    @ManyToMany
    private Set<UserProfile> participants  = new HashSet<>();
}