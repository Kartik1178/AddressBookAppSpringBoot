package com.bridgelabz.addressbookapp.dto;

/**
 * Data Transfer Object for AddressBook REST requests.
 * Lombok annotations auto-generate getters, setters, constructors
 * and toString at compile time, eliminating boilerplate code.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressBookDTO {

    private String name;
    private String phoneNumber;
}
