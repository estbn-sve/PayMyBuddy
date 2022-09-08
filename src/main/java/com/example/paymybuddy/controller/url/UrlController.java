package com.example.paymybuddy.controller.url;

import com.example.paymybuddy.controller.dto.*;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.service.url.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("http://localhost:4200")
public class UrlController {
    @Autowired
    private UrlService service;

    @PostMapping("/newTransaction")
    public ResponseEntity<SendTransaction> newTransactionController(@RequestBody MoneyTransfertRequest moneyTransfertRequest){
        log.info("POST /newTransaction/" + moneyTransfertRequest);
        return ResponseEntity.ok(service.newTransactionService(moneyTransfertRequest));
    }


    @GetMapping("/sendTransaction/{id}")
    public Iterable<SendTransaction> getSendTransaction(@PathVariable("id") final Integer id){
        log.info("Start GET /sendTransaction/"+id);
        return service.getUserTransaction(id);
    }

    @GetMapping("/userEmail/{email}")
    public UserDTO getUserByEmail(@PathVariable("email")final String email) {
        log.info("Start GET /user/" + email);
        return service.getUserByEmail(email);
    }

    @PostMapping("/addContact")
    public ResponseEntity<User> addUserContact(@RequestBody ContactRequest contactRequest){
        log.info("POST /addContact/");
        return ResponseEntity.ok(service.addContact(contactRequest));
    }

    @PostMapping("/addOut")
    public ResponseEntity<Transaction> addOut(@RequestBody OutRequest outRequest){
        log.info("POST /addOut/"+outRequest);
        return ResponseEntity.ok(service.addOut(outRequest));
    }

    @PostMapping("/sendOut")
    public ResponseEntity<Transaction> sendOut(@RequestBody OutRequest outRequest){
        log.info("POST /sendOut/"+outRequest);
        return ResponseEntity.ok(service.sendOut(outRequest));
    }
}
