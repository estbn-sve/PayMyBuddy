package com.example.paymybuddy.controller.dto;

import com.example.paymybuddy.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
public class SendTransaction {
    Double solde_to;
    Double solde_from;
    Double solde_app;
    Date date;
    Integer id_user_to;
    Integer id_user_from;
}
