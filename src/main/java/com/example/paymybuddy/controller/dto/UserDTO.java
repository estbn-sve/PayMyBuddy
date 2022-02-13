package com.example.paymybuddy.controller.dto;

import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserLogin;
import lombok.Data;

import javax.persistence.*;
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
