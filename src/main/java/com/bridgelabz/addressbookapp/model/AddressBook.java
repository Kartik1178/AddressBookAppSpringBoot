package com.bridgelabz.addressbookapp.model;

/**
 * Model class representing a single AddressBook entry.
 * Stored in an in-memory list at this stage.
 * Will be annotated with JPA Entity annotations when the DB layer is added.
 */
public class AddressBook {

    private int    id;
    private String name;
    private String phoneNumber;

    // Default no-args constructor
    public AddressBook() {}

    // Parameterised constructor for creating a fully initialised entry
    public AddressBook(int id, String name, String phoneNumber) {
        this.id          = id;
        this.name        = name;
        this.phoneNumber = phoneNumber;
    }

    // Getter for id
    public int getId() { return id; }

    // Setter for id
    public void setId(int id) { this.id = id; }

    // Getter for name
    public String getName() { return name; }

    // Setter for name
    public void setName(String name) { this.name = name; }

    // Getter for phoneNumber
    public String getPhoneNumber() { return phoneNumber; }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
