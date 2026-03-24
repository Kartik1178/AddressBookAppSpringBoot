package com.bridgelabz.addressbookapp.service;

/**
 * Service layer for the Address Book application.
 * Throws AddressBookException when a requested entry cannot be found,
 * allowing GlobalExceptionHandler to return a structured error response.
 */
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private final AtomicInteger     idCounter       = new AtomicInteger(1);

    // Returns all entries currently held in memory
    public List<AddressBook> getAllAddressBook() {
        log.debug("Retrieving all entries, count: {}", addressBookList.size());
        return addressBookList;
    }

    // Returns entry matching id or throws AddressBookException if absent
    public AddressBook getAddressBookById(int id) {
        log.debug("Retrieving entry with id: {}", id);
        return addressBookList.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AddressBookException(
                        "AddressBook entry with id " + id + " was not found"));
    }

    // Creates and stores a new AddressBook entry built from the validated DTO
    public AddressBook createAddressBook(AddressBookDTO dto) {
        AddressBook entry = new AddressBook(idCounter.getAndIncrement(),
                                            dto.getName(),
                                            dto.getPhoneNumber());
        addressBookList.add(entry);
        log.info("Created entry: {}", entry);
        return entry;
    }

    // Updates fields of the entry identified by id; throws if not found
    public AddressBook updateAddressBook(int id, AddressBookDTO dto) {
        AddressBook entry = getAddressBookById(id);
        entry.setName(dto.getName());
        entry.setPhoneNumber(dto.getPhoneNumber());
        log.info("Updated entry: {}", entry);
        return entry;
    }

    // Removes the entry identified by id; throws AddressBookException if not found
    public void deleteAddressBook(int id) {
        AddressBook entry = getAddressBookById(id);
        addressBookList.remove(entry);
        log.info("Deleted entry with id: {}", id);
    }
}
