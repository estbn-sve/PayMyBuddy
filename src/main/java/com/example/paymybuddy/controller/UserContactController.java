package com.example.paymybuddy.controller;

//import com.example.paymybuddy.model.Transaction;
//import com.example.paymybuddy.model.UserContact;
//import com.example.paymybuddy.service.UserContactService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.NoSuchElementException;
//
//@RestController
//@Slf4j
//@RequestMapping("/contact")
//@CrossOrigin("http://localhost:4200")
//public class UserContactController {
//
//    @Autowired
//    private UserContactService service;
//
//    @GetMapping("")
//    public Iterable<UserContact> getAllContact(){
//        log.info("GET /contact");
//        return service.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserContact> getContact(@PathVariable("id") final Integer id){
//        log.info("GET /contact/"+id);
//        try {
//            return ResponseEntity.ok(service.getUserContact(id));
//        } catch(NoSuchElementException e){
//            log.error("GET /contact/"+id +" Error : "+e.getMessage());
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
