package com.bridgelabz.addressbookapp.model;

/**
 * Model class representing a single AddressBook entry.
 * Lombok annotations generate all boilerplate accessor methods
 * and constructors at compile time via annotation processing.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressBook {

    private int    id;
    private String name;
    private String phoneNumber;
}
