package com.example.eventcontroller.events.models;

import com.example.eventcontroller.events.models.enums.ECategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private ECategory category;

    @NotBlank
    private String location;

    @NotBlank
    private LocalDateTime startTime;

    @NotBlank
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "organiser_id")
    private Organiser organiser;

    @OneToMany
    @JoinTable(
            name = "event_attendances",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendance_id")
    )
    private List<Attendance> attendances;

    @OneToMany
    @JoinTable(
            name = "event_reviews",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews;

    public Event(String name, String description, ECategory category, String location, LocalDateTime startTime, LocalDateTime endTime, Organiser organiser) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organiser = organiser;
    }

}
