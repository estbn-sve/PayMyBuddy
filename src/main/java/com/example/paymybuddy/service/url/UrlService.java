package com.example.paymybuddy.service.url;

import com.example.paymybuddy.controller.dto.MoneyTransfertRequest;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.repository.UsersRepository;
import com.example.paymybuddy.service.TransactionService;
import com.example.paymybuddy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UrlService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TransactionService transactionService;

    public Transaction newTransactionService(final MoneyTransfertRequest moneyTransfertRequest){
        Date date = new Date();
        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setSolde_to(moneyTransfertRequest.solde_to);
        transaction.setSolde_app(moneyTransfertRequest.solde_app);
        transaction.setSolde_from(moneyTransfertRequest.solde_from);
        transaction.setId_user_from(usersService.getUser(Integer.valueOf(moneyTransfertRequest.id_user_from)));
        transaction.setId_user_to(usersService.getUser(Integer.valueOf(moneyTransfertRequest.id_user_to)));
        return transactionService.addTransactions(transaction);
    }
}
