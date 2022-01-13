package com.example.paymybuddy.controller.url;

import com.example.paymybuddy.controller.dto.MoneyTransfertRequest;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.service.url.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
