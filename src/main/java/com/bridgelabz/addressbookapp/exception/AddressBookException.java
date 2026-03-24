package com.bridgelabz.addressbookapp.exception;

/**
 * Custom unchecked exception for the Address Book application.
 * Thrown by the service layer when a requested entry cannot be found
 * or any other domain-specific error condition is encountered.
 */
public class AddressBookException extends RuntimeException {

    // Constructs the exception with a descriptive error message
    public AddressBookException(String message) {
        super(message);
    }
}
