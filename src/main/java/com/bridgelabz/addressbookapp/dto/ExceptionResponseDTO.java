package com.bridgelabz.addressbookapp.dto;

/**
 * DTO used to return a structured error payload to the client
 * whenever a validation failure or business exception is raised.
 * Lombok generates constructors and accessors automatically.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDTO {

    private String message;
    private int    statusCode;
}
