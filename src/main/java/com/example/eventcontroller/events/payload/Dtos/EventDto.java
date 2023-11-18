package com.example.eventcontroller.events.payload.Dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EventDto {

    private String name;
    private LocalDateTime date;
    private String location;
    private String description;
}
