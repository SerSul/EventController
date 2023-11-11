package com.example.eventcontroller.events.payload.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JsonResponseHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<?> createSuccessResponse(Object response) {
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> createSuccessResponseWMessage(String message) {
        ObjectNode jsonResponse = objectMapper.createObjectNode();
        jsonResponse.put("message", message);
        return ResponseEntity.ok(jsonResponse);
    }

    public ResponseEntity<?> createNotFoundResponse(String message) {
        ObjectNode jsonResponse = objectMapper.createObjectNode();
        jsonResponse.put("message", message);
        return ResponseEntity.badRequest().body(jsonResponse);
    }

    public ResponseEntity<?> createProcessingErrorResponse() {
        return ResponseEntity.badRequest().body("{\"message\": \"Ошибка обработки данных\"}");
    }
}

