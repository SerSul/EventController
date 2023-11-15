package com.example.eventcontroller.events.models;

import com.example.eventcontroller.auth.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class UserProfile {

    public UserProfile(String firstName, String secondName, int age, eventRoles role, User user) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.role = role;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private int age;

    @Enumerated(EnumType.STRING)
    private eventRoles role;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> organizedEvents = new HashSet<>();

    @ManyToMany(mappedBy = "registeredUsers", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();
}


