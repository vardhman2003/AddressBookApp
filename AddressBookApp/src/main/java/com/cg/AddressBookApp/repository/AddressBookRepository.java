package com.cg.AddressBookApp.repository;

import com.cg.AddressBookApp.model.AddressBookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBookModel,Integer> {

}
