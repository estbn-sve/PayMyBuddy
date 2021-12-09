package com.example.paymybuddy.repository;

import com.example.paymybuddy.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact,Integer> {
}
