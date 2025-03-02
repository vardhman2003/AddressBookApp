package com.cg.AddressBookApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddressBookDTO {

    private int id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
