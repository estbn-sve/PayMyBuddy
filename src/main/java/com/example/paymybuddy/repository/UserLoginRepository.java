package com.example.paymybuddy.repository;

import com.example.paymybuddy.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    Optional<UserLogin> findByIdentifiantAndMdp(String identifiant, String pass);
}
