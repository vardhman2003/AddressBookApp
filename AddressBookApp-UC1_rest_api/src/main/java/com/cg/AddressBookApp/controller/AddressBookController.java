package com.cg.AddressBookApp.controller;
import com.cg.AddressBookApp.DTO.AddressBookDTO;
import com.cg.AddressBookApp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressBookController {

    private final AddressBookService addressBookService;

    @Autowired
    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    //GET all address entries
    @GetMapping
    public ResponseEntity<List<AddressBookDTO>> getAllAddresses() {
        List<AddressBookDTO> addresses = addressBookService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    //GET address entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDTO> getAddressById(@PathVariable int id) {
        Optional<AddressBookDTO> address = addressBookService.getAddressById(id);
        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //POST to create a new address entry
    @PostMapping
    public ResponseEntity<AddressBookDTO> createAddress(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBookDTO savedAddress = addressBookService.createAddress(addressBookDTO);
        return ResponseEntity.status(201).body(savedAddress);  //HTTP 201 Created
    }

    //PUT to update an existing address entry by ID
    @PutMapping("/{id}")
    public ResponseEntity<AddressBookDTO> updateAddress(@PathVariable int id, @RequestBody AddressBookDTO updatedAddressDTO) {
        Optional<AddressBookDTO> updated = addressBookService.updateAddress(id, updatedAddressDTO);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //DELETE an address entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        boolean isDeleted = addressBookService.deleteAddress(id);
        return isDeleted ? ResponseEntity.noContent().build() //HTTP 204 No Content
                : ResponseEntity.notFound().build();  //HTTP 404 Not Found
    }
}
