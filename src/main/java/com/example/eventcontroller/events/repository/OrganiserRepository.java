package com.example.eventcontroller.events.repository;

import com.example.eventcontroller.events.models.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganiserRepository extends JpaRepository<Organiser, Long> {

    public Organiser getOrganiserByUserId(Long id);
}
