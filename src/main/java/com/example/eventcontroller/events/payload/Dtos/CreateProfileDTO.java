package com.example.eventcontroller.events.payload.Dtos;

import com.example.eventcontroller.events.models.eventRoles;
import lombok.Data;

@Data
public class CreateProfileDTO {
    private String firstName;
    private String secondName;
    private int age;
    private eventRoles role;
}
