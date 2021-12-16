package com.example.paymybuddy;

import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserContact;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.service.TransactionService;
import com.example.paymybuddy.service.UserContactService;
import com.example.paymybuddy.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class IntegrationsTest {

    @Autowired
    UsersService usersService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserContactService userContactService;


    @Test
    public void addUser(){
        UserLogin userLogin = new UserLogin();
        userLogin.setIdentifiant("tom");
        userLogin.setMdp("david");

        User user = new User();
        user.setLastName("tom");
        user.setFirstName("david");
        user.setSolde(4d);
        user.setIban("iban");
        user.setEmail("tom.david@email.fr");
        user.setUserLogin(userLogin);

        User user1 = usersService.addUser(user);
        User newUser = usersService.getUser(user1.getId());
    }

    @Test
    public void addTransaction(){
        User userTo = usersService.getUser(1);
        User userFrom = usersService.getUser(2);
        Date date = new Date();


        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setId_user_from(userFrom);
        transaction.setId_user_to(userTo);
        transaction.setSolde_app(0.50);
        transaction.setSolde_from(10.00);
        transaction.setSolde_to(9.50);

        Transaction transaction1 = transactionService.addTransactions(transaction);
        Transaction newTrasaction = transactionService.getTransactions(transaction1.getId());
    }

    @Test
    public void addUserContact(){
        User userFirst = usersService.getUser(1);
        User userSecond = usersService.getUser(2);

        UserContact userContact = new UserContact();
        userContact.setId_user_first(userFirst);
        userContact.setId_user_second(userSecond);


        UserContact userContact1 = userContactService.addUserContact(userContact);
        UserContact newUserContact = userContactService.getUserContact(userContact1.getId());
    }
}
