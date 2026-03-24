package com.bridgelabz.addressbookapp.dto;

/**
 * Data Transfer Object for AddressBook REST requests.
 * Bean Validation annotations enforce field constraints before
 * the service layer processes the request.
 * @Valid on the controller method activates these constraints.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressBookDTO {

    @NotEmpty(message = "Name must not be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$",
             message = "Name must start with a capital letter and be at least 3 characters long")
    private String name;

    @NotEmpty(message = "Phone number must not be empty")
    @Pattern(regexp = "^[6-9]\\d{9}$",
             message = "Phone number must be a valid 10-digit Indian mobile number starting with 6-9")
    private String phoneNumber;
}
