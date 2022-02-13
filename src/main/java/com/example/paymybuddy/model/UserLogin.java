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
@Table(name = "user_login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "user_login_identifiant")
    String identifiant;
    @Column(name = "user_login_mdp")
    String mdp;
    @OneToOne(mappedBy = "userLogin")
    User user;
}
