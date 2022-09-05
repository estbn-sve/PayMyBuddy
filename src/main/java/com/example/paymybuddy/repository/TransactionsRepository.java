package com.example.paymybuddy.repository;

import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {
    Iterable<Transaction> findAllByUserFromOrUserTo(User userFrom, User userTo);
}
