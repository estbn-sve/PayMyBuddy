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
        if(id == null || !repository.existsById(id)){
            return repository.save(transaction);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addTransaction"+id));
        }
    }

    public Transaction putTransactions (Transaction currentTransaction , final Integer id){
//        if (repository.existsById(id)){
            Transaction transaction = currentTransaction;
            //currentTransaction = repository.findById(id).get();
            currentTransaction = repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putPerson "+id));

            Date date = transaction.getDate();
            if (date != null) {
                currentTransaction.setDate(date);
            }
            User id_users_from = transaction.getUserFrom();
            if (id_users_from !=null){
                currentTransaction.setUserFrom(id_users_from);
            }
            Double solde_from = transaction.getSoldeFrom();
            if(solde_from != null){
                currentTransaction.setSoldeFrom(solde_from);
            }
            Double solde_to = transaction.getSoldeTo();
            if(solde_to != null){
                currentTransaction.setSoldeTo(solde_to);
            }
            Double solde_app = transaction.getSoldeApp();
            if(solde_app != null){
                currentTransaction.setSoldeApp(solde_app);
            }
            return currentTransaction;
    }


}
