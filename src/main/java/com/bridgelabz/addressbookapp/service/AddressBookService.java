package com.bridgelabz.addressbookapp.service;

/**
 * Service layer for the Address Book application.
 * Responsible for managing CRUD operations on AddressBook models.
 * Data is stored in an in-memory ArrayList at this stage;
 * persistence to MySQL will be added in a later use case.
 */
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private final AtomicInteger     idCounter       = new AtomicInteger(1);

    // Returns all address book entries currently in memory
    public List<AddressBook> getAllAddressBook() {
        return addressBookList;
    }

    // Returns a single entry by id, or null when not found
    public AddressBook getAddressBookById(int id) {
        return addressBookList.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Creates a new AddressBook entry from the given DTO and stores it
    public AddressBook createAddressBook(AddressBookDTO dto) {
        AddressBook entry = new AddressBook(idCounter.getAndIncrement(),
                                            dto.getName(),
                                            dto.getPhoneNumber());
        addressBookList.add(entry);
        return entry;
    }

    // Updates name and phoneNumber of an existing entry; returns null if id not found
    public AddressBook updateAddressBook(int id, AddressBookDTO dto) {
        AddressBook entry = getAddressBookById(id);
        if (entry != null) {
            entry.setName(dto.getName());
            entry.setPhoneNumber(dto.getPhoneNumber());
        }
        return entry;
    }

    // Removes the entry with the given id; returns true if removed
    public boolean deleteAddressBook(int id) {
        return addressBookList.removeIf(a -> a.getId() == id);
    }
}
