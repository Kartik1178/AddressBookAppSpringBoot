package com.bridgelabz.addressbookapp.dto;

/**
 * Generic response wrapper returned from all REST endpoints.
 * Carries a human-readable message and a data payload object.
 */
public class ResponseDTO {

    private String message;
    private Object data;

    // Default no-args constructor
    public ResponseDTO() {}

    // Parameterised constructor
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data    = data;
    }

    // Getter for message
    public String getMessage() { return message; }

    // Setter for message
    public void setMessage(String message) { this.message = message; }

    // Getter for data
    public Object getData() { return data; }

    // Setter for data
    public void setData(Object data) { this.data = data; }
}
