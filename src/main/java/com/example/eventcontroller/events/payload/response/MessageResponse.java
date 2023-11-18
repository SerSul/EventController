package com.example.eventcontroller.events.payload.response;

import lombok.Data;

@Data
public class MessageResponse {

    private String status;
    private String message;
    private Object object;

    public MessageResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public MessageResponse(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object=object;
    }

    public MessageResponse(String status, Object object) {
        this.status = status;
        this.object = object;
    }

    public MessageResponse() {

    }
}
