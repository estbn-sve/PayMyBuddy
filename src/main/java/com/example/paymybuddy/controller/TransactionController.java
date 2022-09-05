package com.example.paymybuddy.controller;

import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("")
    public Iterable<Transaction> getAllTransaction(){
        log.info("GET /transaction");
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") final Integer id){
        log.info("GET /transaction/"+id);
        try {
            return ResponseEntity.ok(service.getTransactions(id));
        } catch(NoSuchElementException e){
            log.error("GET /transaction/"+id +" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        log.info("POST /transaction/");
        try {
            return ResponseEntity.ok(service.addTransactions(transaction));
        } catch (NoSuchElementException e){
            log.error("POST /transaction/" +" Error : "+e.getMessage());
            return  ResponseEntity.notFound().build();
        }
    }
}
