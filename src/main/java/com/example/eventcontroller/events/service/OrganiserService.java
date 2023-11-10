package com.example.eventcontroller.events.service;


import com.example.eventcontroller.events.models.Organiser;
import com.example.eventcontroller.events.repository.OrganiserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Data
public class OrganiserService {

    @Autowired
    OrganiserRepository organiserRepository;


    public Organiser createOrganiser(Organiser organiser)
    {
        return organiserRepository.save(organiser);
    }

}