package com.example.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "transaction")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idTransaction")
    Integer id;
    @Column (name = "transaction_solde_to")
    Double solde_to;
    @Column (name = "transaction_solde_from")
    Double solde_from;
    @Column (name = "transaction_solde_app")
    Double solde_app;
    @Column (name = "transaction_date")
    Date date;
    @OneToOne
    @JoinColumn(name = "users_to_id")
    Users id_user_to;
    @OneToOne
    @JoinColumn (name = "users_from_id")
    Users id_user_from;
}
