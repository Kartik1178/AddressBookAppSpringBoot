package com.bridgelabz.addressbookapp.controller;

/**
 * REST Controller for the Address Book application.
 * @Valid on POST and PUT request bodies triggers Bean Validation constraints
 * defined in AddressBookDTO before the service layer is invoked.
 * All exceptions are handled centrally by GlobalExceptionHandler.
 */
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
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

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    // Logs request and returns all stored address book entries
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAddressBook() {
        log.info("GET /addressbookservice/");
        ResponseDTO response = new ResponseDTO("Fetched All AddressBook Entries",
                                               addressBookService.getAllAddressBook());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Logs request and returns the entry matching the given id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressBookById(@PathVariable int id) {
        log.info("GET /addressbookservice/get/{}", id);
        ResponseDTO response = new ResponseDTO("Fetched AddressBook Entry",
                                               addressBookService.getAddressBookById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Validates request body then delegates creation to the service layer
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddressBook(
            @Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("POST /addressbookservice/create - {}", addressBookDTO);
        ResponseDTO response = new ResponseDTO("Created AddressBook Entry",
                                               addressBookService.createAddressBook(addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Validates request body then delegates update by id to the service layer
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBook(
            @PathVariable int id,
            @Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("PUT /addressbookservice/update/{} - {}", id, addressBookDTO);
        ResponseDTO response = new ResponseDTO("Updated AddressBook Entry",
                                               addressBookService.updateAddressBook(id, addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Logs request and delegates deletion by id to the service layer
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddressBook(@PathVariable int id) {
        log.info("DELETE /addressbookservice/delete/{}", id);
        addressBookService.deleteAddressBook(id);
        ResponseDTO response = new ResponseDTO("Deleted AddressBook Entry with id: " + id, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
