package com.example.paymybuddy.controller.dto;

import lombok.Data;

@Data
public class SignInRequest {
        String firstName;
        String lastName;
        String email;
        String user;
        String mdp;
}
