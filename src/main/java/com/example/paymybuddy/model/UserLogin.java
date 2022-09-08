package com.example.paymybuddy.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_login")
public class UserLogin {
    //TODO modifier generationType en Sequence
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
