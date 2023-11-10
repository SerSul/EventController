package com.example.eventcontroller.events.payload.Dtos;


import com.example.eventcontroller.events.models.enums.ECategory;

import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
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


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime endTime;


    @Valid
    private OrganiserDTO organiserDTO;



}
