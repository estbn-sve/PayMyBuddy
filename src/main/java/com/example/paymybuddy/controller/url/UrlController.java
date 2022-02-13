package com.example.paymybuddy.controller.url;

import com.example.paymybuddy.controller.dto.ContactUserForUser;
import com.example.paymybuddy.controller.dto.MoneyTransfertRequest;
import com.example.paymybuddy.controller.dto.SendTransaction;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.service.url.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@CrossOrigin("http://localhost:4200")
public class UrlController {
    @Autowired
    private UrlService service;

    @PostMapping("/newTransaction")
    public ResponseEntity<Transaction> newTransactionController(@RequestBody MoneyTransfertRequest moneyTransfertRequest){
        log.info("POST /newTransaction/");
        return ResponseEntity.ok(service.newTransactionService(moneyTransfertRequest));
    }

    @GetMapping("/sendTransaction/{id}")
    public Iterable<SendTransaction> getSendTransaction(@PathVariable("id") final Integer id){
        log.info("Start GET /sendTransaction/"+id);
        return service.getUserTransaction(id);
    }

//    @GetMapping("/UserContact/{id}")
//    public Iterable<ContactUserForUser> getUserContact(@PathVariable("id") final Integer id){
//        log.info("Start GET /UserContact/"+id);
//        return service.getUserContact(id);
//    }

}
