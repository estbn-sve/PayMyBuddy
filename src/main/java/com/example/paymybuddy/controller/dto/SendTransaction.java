package com.example.paymybuddy.controller.dto;

import lombok.Data;

import java.util.Date;
@Data
public class SendTransaction {
    Double solde_to;
    Double solde_from;
    Double solde_app;
    Date date;
    UserDTO id_user_to;
    UserDTO id_user_from;
    String iban;
}
