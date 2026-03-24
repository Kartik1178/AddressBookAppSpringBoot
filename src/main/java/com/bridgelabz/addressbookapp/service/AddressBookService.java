package com.bridgelabz.addressbookapp.service;

/**
 * Service layer for the Address Book application.
 * Lombok @Slf4j activates structured SLF4J logging at class level.
 * Logging levels and output targets are configured in application-*.properties.
 */
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
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

    // Returns all entries and logs the current count at DEBUG level
    public List<AddressBook> getAllAddressBook() {
        log.debug("Retrieving all entries, count: {}", addressBookList.size());
        return addressBookList;
    }

    // Returns a single entry by id; logs at DEBUG, returns null if not found
    public AddressBook getAddressBookById(int id) {
        log.debug("Retrieving entry with id: {}", id);
        return addressBookList.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Creates a new entry, persists it in memory and logs at INFO level
    public AddressBook createAddressBook(AddressBookDTO dto) {
        AddressBook entry = new AddressBook(idCounter.getAndIncrement(),
                                            dto.getName(),
                                            dto.getPhoneNumber());
        addressBookList.add(entry);
        log.info("Created address book entry: {}", entry);
        return entry;
    }

    // Updates an existing entry and logs; warns if id is not found
    public AddressBook updateAddressBook(int id, AddressBookDTO dto) {
        AddressBook entry = getAddressBookById(id);
        if (entry != null) {
            entry.setName(dto.getName());
            entry.setPhoneNumber(dto.getPhoneNumber());
            log.info("Updated address book entry: {}", entry);
        } else {
            log.warn("Entry with id: {} not found for update", id);
        }
        return entry;
    }

    // Removes an entry by id and logs the outcome at INFO or WARN level
    public boolean deleteAddressBook(int id) {
        boolean removed = addressBookList.removeIf(a -> a.getId() == id);
        if (removed) log.info("Deleted entry with id: {}", id);
        else         log.warn("Entry with id: {} not found for deletion", id);
        return removed;
    }
}
