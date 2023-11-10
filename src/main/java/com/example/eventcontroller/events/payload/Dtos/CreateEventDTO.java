package com.example.eventcontroller.events.payload.Dtos;

import com.example.eventcontroller.events.models.Attendance;
import com.example.eventcontroller.events.models.Organiser;
import com.example.eventcontroller.events.models.enums.ECategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Data
public class CreateEventDTO {
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

    private OrganiserDTO organiserDTO;


    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTime.format(formatter);
    }
}
