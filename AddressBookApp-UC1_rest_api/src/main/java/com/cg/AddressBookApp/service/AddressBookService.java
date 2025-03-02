package com.cg.AddressBookApp.service;

import com.cg.AddressBookApp.DTO.AddressBookDTO;
import com.cg.AddressBookApp.model.AddressBookModel;
import com.cg.AddressBookApp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    @Autowired
    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    // Convert AddressBook entity to AddressBookDTO
    private AddressBookDTO convertToDTO(AddressBookModel addressBook) {
        return new AddressBookDTO(
                addressBook.getId(),
                addressBook.getName(),
                addressBook.getEmail(),
                addressBook.getPhoneNumber(),
                addressBook.getAddress()
        );
    }

    //Convert AddressBookDTO to AddressBook entity
    private AddressBookModel convertToEntity(AddressBookDTO addressBookDTO) {
        AddressBookModel addressBook = new AddressBookModel();
        addressBook.setId(addressBookDTO.getId());
        addressBook.setName(addressBookDTO.getName());
        addressBook.setEmail(addressBookDTO.getEmail());
        addressBook.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBook.setAddress(addressBookDTO.getAddress());
        return addressBook;
    }

    // Get all address entries
    public List<AddressBookDTO> getAllAddresses() {
        List<AddressBookModel> addresses = addressBookRepository.findAll();
        return addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Get address entry by ID
    public Optional<AddressBookDTO> getAddressById(int id) {
        Optional<AddressBookModel> address = addressBookRepository.findById(id);
        return address.map(this::convertToDTO);
    }

    //Create a new address entry
    public AddressBookDTO createAddress(AddressBookDTO addressBookDTO) {
        AddressBookModel addressBook = convertToEntity(addressBookDTO);
        AddressBookModel savedAddress = addressBookRepository.save(addressBook);
        return convertToDTO(savedAddress);
    }

    //Update an existing address entry by ID
    public Optional<AddressBookDTO> updateAddress(int id, AddressBookDTO updatedAddressDTO) {
        return addressBookRepository.findById(id)
                .map(address -> {
                    address.setName(updatedAddressDTO.getName());
                    address.setEmail(updatedAddressDTO.getEmail());
                    address.setPhoneNumber(updatedAddressDTO.getPhoneNumber());
                    address.setAddress(updatedAddressDTO.getAddress());
                    AddressBookModel savedAddress = addressBookRepository.save(address);
                    return convertToDTO(savedAddress);
                });
    }

    //Delete an address entry by ID
    public boolean deleteAddress(int id) {
        return addressBookRepository.findById(id)
                .map(address -> {
                    addressBookRepository.delete(address);
                    return true;
                }).orElse(false);
    }
}
