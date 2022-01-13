package com.example.paymybuddy.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MoneyTransfertRequest {
    public String id_user_from;
    public String id_user_to;
    public Double solde_app;
    public Double solde_from;
    public Double solde_to;

}
