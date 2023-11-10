package com.example.eventcontroller.events.payload.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganiserDTO {
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    @NotBlank
    private int age;
}
