package com.example.paymybuddy.service;

import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    @Autowired
    private TransactionsRepository repository;

    public Transaction getTransactions(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getTransactions"+id));
    }

    public Iterable<Transaction> getAllTransactions(){
        return repository.findAll();
    }


    public Transaction addTransactions(Transaction transaction){
        Integer id = transaction.getId();
        if(!repository.existsById(id)){
            return repository.save(transaction);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addTransaction"+id));
        }
    }

    public Transaction putTransactions (Transaction currentTransaction , final Integer id){
        if(repository.existsById(id)){
            return currentTransaction;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putTransaction "+id));
        }
    }


}
