package com.example.eventcontroller.events.service;

import com.example.eventcontroller.events.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Data
@AllArgsConstructor
@Service
@NoArgsConstructor

public class ReviewService {
    @Autowired
    private  ReviewRepository reviewRepository;





}
