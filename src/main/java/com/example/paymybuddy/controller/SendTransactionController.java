package com.example.paymybuddy.controller;

import com.example.paymybuddy.service.AppSoldeService;
import com.example.paymybuddy.service.TransactionService;
import com.example.paymybuddy.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Slf4j
@RequestMapping("/send")
@CrossOrigin("http://localhost:4200")
public class SendTransactionController {

    @Autowired
    private TransactionService tService;
    @Autowired
    private UsersService uService;
    @Autowired
    private AppSoldeService appService;

    //Crée une nouvelle transaction puis modiffie les soldes des utilisateur et ajoute au solde de l'app la commission.
    //transaction SQL
    //@Transactional

}
