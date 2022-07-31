package com.example.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_login")
public class UserLogin {
    //TODO modifier generationType en Sequence
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "sequence_generator")
//    @GenericGenerator(
//            name = "sequence_generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name",value = "user_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "455"),
//            }
//    )
    @Column(name = "id")
    Integer id;
    @Column(name = "user_login_identifiant")
    String identifiant;
    @Column(name = "user_login_mdp")
    String mdp;
    @OneToOne(mappedBy = "userLogin")
    User user;
}
