package com.example.eventcontroller.events.models;

import com.example.eventcontroller.events.models.enums.EARole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    @NotBlank
    private int age;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private EARole role;

    public Attendance(User user, String first_name, String last_name, int age) {
        this.user = user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.role=EARole.ROLE_VISITOR;
    }

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;



}
