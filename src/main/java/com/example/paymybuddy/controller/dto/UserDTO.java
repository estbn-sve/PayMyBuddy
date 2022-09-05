package com.example.paymybuddy.controller.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    Integer id;
    String lastName;
    String firstName;
    String email;
    Double solde;
    String iban;
    List<UserContactDTO> friendList;
}
