package com.bridgelabz.addressbookapp.controller;

/**
 * REST Controller for the Address Book application.
 * Delegates all business logic to AddressBookService via constructor injection.
 * Returns responses wrapped in ResponseEntity containing a ResponseDTO.
 */
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    // Returns all address book entries from the service layer
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAddressBook() {
        ResponseDTO response = new ResponseDTO("Fetched All AddressBook Entries",
                                               addressBookService.getAllAddressBook());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Returns a single address book entry by id via the service layer
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressBookById(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO("Fetched AddressBook Entry",
                                               addressBookService.getAddressBookById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delegates creation of a new entry to the service and returns the saved model
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddressBook(@RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Created AddressBook Entry",
                                               addressBookService.createAddressBook(addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Delegates update of an existing entry by id to the service
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBook(@PathVariable int id,
                                                         @RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Updated AddressBook Entry",
                                               addressBookService.updateAddressBook(id, addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delegates deletion of an entry by id to the service
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddressBook(@PathVariable int id) {
        addressBookService.deleteAddressBook(id);
        ResponseDTO response = new ResponseDTO("Deleted AddressBook Entry with id: " + id, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
