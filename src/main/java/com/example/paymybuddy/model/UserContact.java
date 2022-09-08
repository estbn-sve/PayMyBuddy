package com.example.paymybuddy.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_contact")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @OneToOne
    @JoinColumn (name = "user_id_first")
    User id_user_first;
    @OneToOne
    @JoinColumn (name = "user_id_second")
    User id_user_second;
}
