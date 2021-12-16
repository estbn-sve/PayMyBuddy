package com.example.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "user_last_name")
    String lastName;
    @Column(name = "user_first_name")
    String firstName;
    @Column(name = "user_email")
    String email;
    @Column(name = "user_solde")
    Double solde;
    @Column(name = "user_iban")
    String iban;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_login_id")
    UserLogin userLogin;

}
