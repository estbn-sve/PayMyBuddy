package com.example.paymybuddy.controller;

import com.example.paymybuddy.controller.dto.LoginRequest;
import com.example.paymybuddy.controller.dto.SignInRequest;
import com.example.paymybuddy.controller.dto.UserContactDTO;
import com.example.paymybuddy.controller.dto.UserDTO;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.service.UserLoginService;
import com.example.paymybuddy.service.UsersService;
import com.example.paymybuddy.service.url.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UsersService service;

    @Autowired
    private UserLoginService loginService;

    @Autowired
    private UrlService urlService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") final Integer id){
        log.info("GET /user/"+id);
        try {
            User u = service.getUser(id);
            return ResponseEntity.ok(u);
        } catch (NoSuchElementException e){
            log.error("GET /user/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequest loginRequest){
        log.info("GET /user/login");
        try {
            UserDTO u = loginService.getUserLogin(loginRequest.getUser(), loginRequest.getMdp());
            return ResponseEntity.ok(u);
        } catch (NoSuchElementException e){
            log.error("GET /user/"+loginRequest+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@RequestBody SignInRequest signInRequest){
        log.info("POST /user/signin");
        try {
            return ResponseEntity.ok(urlService.signIn(signInRequest));
        }catch (NoSuchElementException e){
            log.error("POST /user/"+signInRequest+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contact/{id}")
    public List<UserContactDTO> contact (@PathVariable("id") final Integer id){
        log.info(("GET /contact/"+id));
        return urlService.getUserContact(id);
    }
}
