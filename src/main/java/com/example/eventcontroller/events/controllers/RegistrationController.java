package com.example.eventcontroller.events.controllers;

//import com.example.eventcontroller.events.service.RegistrationService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/registration")
//public class RegistrationController {
//
//    private final RegistrationService registrationService;
//
//    public RegistrationController( RegistrationService registrationService) {
//        this.registrationService = registrationService;
//
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
//        authService.registerUser(registrationDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//    }
//
//    @PostMapping("/cancel")
//    public ResponseEntity<?> cancelRegistration(@RequestBody CancellationDTO cancellationDTO) {
//        authService.cancelRegistration(cancellationDTO);
//        return ResponseEntity.ok("Registration canceled successfully");
//    }
//
//    // Other registration-related endpoints
//
//}
