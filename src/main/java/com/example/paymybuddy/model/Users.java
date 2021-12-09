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
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsers")
    Integer id;
    @Column(name = "user_lastName")
    String lastName;
    @Column(name = "user_firstName")
    String firstName;
    @Column(name = "user_email")
    String email;
    @Column(name = "user_solde")
    Double solde;
    @Column(name = "user_iban")
    String iban;
    @OneToOne
    @JoinColumn(name = "user_login_id")
    UserLogin userLogin;

}
