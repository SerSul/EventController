package com.example.eventcontroller.events.payload.Dtos;

import lombok.Data;

@Data
public class UpdateProfileDTO {
    private String firstName;
    private String secondName;
    private Integer age;
}
