package com.example.eventcontroller.events.service;

import com.example.eventcontroller.events.repository.AttendanceRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@NoArgsConstructor
@Service
public class AttendanceService {
    @Autowired
    private  AttendanceRepository attendanceRepository;

}

