package com.bridgelabz.addressbookapp.controller;

/**
 * REST Controller for the Address Book application.
 * Lombok @Slf4j provides a pre-configured SLF4J logger instance.
 * Every request is logged at INFO level before delegating to the service.
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

@Slf4j
@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    // Logs request and returns all address book entries
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAddressBook() {
        log.info("GET /addressbookservice/");
        ResponseDTO response = new ResponseDTO("Fetched All AddressBook Entries",
                                               addressBookService.getAllAddressBook());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Logs request and returns a single entry by id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressBookById(@PathVariable int id) {
        log.info("GET /addressbookservice/get/{}", id);
        ResponseDTO response = new ResponseDTO("Fetched AddressBook Entry",
                                               addressBookService.getAddressBookById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Logs request and creates a new address book entry
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddressBook(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("POST /addressbookservice/create - payload: {}", addressBookDTO);
        ResponseDTO response = new ResponseDTO("Created AddressBook Entry",
                                               addressBookService.createAddressBook(addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Logs request and updates an existing entry by id
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBook(@PathVariable int id,
                                                         @RequestBody AddressBookDTO addressBookDTO) {
        log.info("PUT /addressbookservice/update/{} - payload: {}", id, addressBookDTO);
        ResponseDTO response = new ResponseDTO("Updated AddressBook Entry",
                                               addressBookService.updateAddressBook(id, addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Logs request and deletes the entry with the given id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddressBook(@PathVariable int id) {
        log.info("DELETE /addressbookservice/delete/{}", id);
        addressBookService.deleteAddressBook(id);
        ResponseDTO response = new ResponseDTO("Deleted AddressBook Entry with id: " + id, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
