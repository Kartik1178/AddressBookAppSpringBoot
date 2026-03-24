package com.bridgelabz.addressbookapp.dto;

/**
 * Data Transfer Object for AddressBook REST requests.
 * Carries fields sent by the client in POST and PUT calls.
 * Validation constraints will be added in a later use case.
 */
public class AddressBookDTO {

    private String name;
    private String phoneNumber;

    // Default no-args constructor
    public AddressBookDTO() {}

    // Getter for name
    public String getName() { return name; }

    // Setter for name
    public void setName(String name) { this.name = name; }

    // Getter for phoneNumber
    public String getPhoneNumber() { return phoneNumber; }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
