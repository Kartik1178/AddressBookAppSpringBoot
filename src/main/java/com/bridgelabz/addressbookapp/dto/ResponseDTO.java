package com.bridgelabz.addressbookapp.dto;

/**
 * Generic response wrapper returned from all REST endpoints.
 * Lombok annotations reduce boilerplate by generating accessors
 * and constructors automatically.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String message;
    private Object data;
}
