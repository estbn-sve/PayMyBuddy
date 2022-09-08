package com.example.paymybuddy.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "transaction")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    Integer id;
    @Column (name = "transaction_solde_to")
    Double soldeTo;
    @Column (name = "transaction_solde_from")
    Double soldeFrom;
    @Column (name = "transaction_solde_app")
    Double soldeApp;
    @Column (name = "transaction_date")
    Date date;
    @OneToOne
    @JoinColumn(name = "user_to_id")
    User userTo;
    @OneToOne
    @JoinColumn (name = "user_from_id")
    User userFrom;
    @Column (name = "iban")
    String iban;
}
