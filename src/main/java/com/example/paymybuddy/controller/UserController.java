package com.example.paymybuddy.controller;

import com.example.paymybuddy.model.User;
import com.example.paymybuddy.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UsersService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") final Integer id){
        log.info("GET /user/"+id);
        try {
            return ResponseEntity.ok(service.getUser(id));
        } catch (NoSuchElementException e){
            log.error("GET /user/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
