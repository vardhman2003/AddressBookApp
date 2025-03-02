package com.cg.AddressBookApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddressBookDTO {

    private int id;

    //Name cannot be null
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    //Email must be valid format and cannot be null or empty.
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    //Phone number must be exactly 10 digits (no spaces or dashes).
    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 characters")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain only digits and be exactly 10 characters long")
    private String phoneNumber;

    //Address cannot be null or empty and must be between 5 and 250 characters.
    @NotBlank(message = "Address cannot be blank")
    @Size(min = 5, max = 250, message = "Address must be between 5 and 250 characters")
    private String address;
}
