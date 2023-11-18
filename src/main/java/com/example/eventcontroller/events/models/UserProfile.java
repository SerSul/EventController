package com.example.eventcontroller.events.models;

import com.example.eventcontroller.auth.models.Role;
import com.example.eventcontroller.auth.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties("user")
public class UserProfile {

    public UserProfile(String firstName, String secondName, int age, User user) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private int age;

    @Enumerated(EnumType.STRING)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}


