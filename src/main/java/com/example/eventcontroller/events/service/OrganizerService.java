package com.example.eventcontroller.events.service;

import com.example.eventcontroller.events.repository.OrganizerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;



}
