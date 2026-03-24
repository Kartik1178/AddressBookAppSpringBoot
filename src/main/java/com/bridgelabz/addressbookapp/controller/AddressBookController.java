package com.bridgelabz.addressbookapp.controller;

/**
 * REST Controller for the Address Book application.
 * Exposes five endpoints covering GET, POST, PUT and DELETE HTTP methods.
 * All responses are wrapped in ResponseEntity containing a ResponseDTO.
 */
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
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

    // Returns a welcome message confirming the service is running
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAddressBook() {
        ResponseDTO response = new ResponseDTO("Address Book Service is up!", "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Returns a single address book entry identified by the given id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressBookById(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO("Get AddressBook Entry by Id", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Creates a new address book entry from the JSON request body
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddressBook(@RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Created AddressBook Entry", addressBookDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Updates an existing address book entry identified by id
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBook(@PathVariable int id,
                                                         @RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Updated AddressBook Entry with id: " + id, addressBookDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Deletes the address book entry identified by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddressBook(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO("Deleted AddressBook Entry with id: " + id, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
