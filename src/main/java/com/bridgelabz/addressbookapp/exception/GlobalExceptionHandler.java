package com.bridgelabz.addressbookapp.exception;

/**
 * Global exception handler for the Address Book REST API.
 * Annotated with @ControllerAdvice so Spring automatically routes
 * all unhandled exceptions thrown during request processing to this class.
 * Each @ExceptionHandler method returns a structured ExceptionResponseDTO.
 */
import com.bridgelabz.addressbookapp.dto.ExceptionResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles Bean Validation failures triggered by @Valid on POST and PUT request bodies
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationException(
            MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errors);
        log.error("Validation failed: {}", errorMessage);
        ExceptionResponseDTO response = new ExceptionResponseDTO(errorMessage,
                                                                  HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handles AddressBookException thrown when an entry id is not found in the store
    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAddressBookException(
            AddressBookException ex) {
        log.error("AddressBook exception: {}", ex.getMessage());
        ExceptionResponseDTO response = new ExceptionResponseDTO(ex.getMessage(),
                                                                  HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Catch-all handler for any unexpected exceptions not handled by other methods
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage());
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
