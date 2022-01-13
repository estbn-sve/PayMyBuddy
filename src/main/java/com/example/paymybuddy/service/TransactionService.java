package com.example.paymybuddy.service;

import com.example.paymybuddy.controller.dto.MoneyTransfertRequest;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    @Autowired
    private TransactionsRepository repository;
    @Autowired
    private UsersService usersService;

    public Transaction getTransactions(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getTransactions"+id));
    }

    public Iterable<Transaction> getAllTransactions(){
        return repository.findAll();
    }

    public List<Transaction> addAllTransactions(List<Transaction> transactionList){
        return repository.saveAll(transactionList);
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
            User id_users_from = transaction.getId_user_from();
            if (id_users_from !=null){
                currentTransaction.setId_user_from(id_users_from);
            }
            Double solde_from = transaction.getSolde_from();
            if(solde_from != null){
                currentTransaction.setSolde_from(solde_from);
            }
            Double solde_to = transaction.getSolde_to();
            if(solde_to != null){
                currentTransaction.setSolde_to(solde_to);
            }
            Double solde_app = transaction.getSolde_app();
            if(solde_app != null){
                currentTransaction.setSolde_app(solde_app);
            }
            return currentTransaction;
//        } else {
//            return repository.findById(id).orElseThrow(()->
//                    new NoSuchElementException("Error with putPerson "+id));
//        }
    }
}
