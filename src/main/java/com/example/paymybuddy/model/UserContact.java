package com.example.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_Contact")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser_contact")
    Integer id;
    @OneToOne
    @JoinColumn (name = "user_id_first")
    Users id_user_first;
    @OneToOne
    @JoinColumn (name = "user_id_second")
    Users id_user_second;
}
