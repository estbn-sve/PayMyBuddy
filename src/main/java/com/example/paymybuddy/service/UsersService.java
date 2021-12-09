package com.example.paymybuddy.service;

import com.example.paymybuddy.model.Users;
import com.example.paymybuddy.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public Users getUser(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getUser"+id));
    }

    public Iterable<Users> getAllUsers(){
        return repository.findAll();
    }

    public Users addUser(Users user){
        Integer id = user.getId();
        if(!repository.existsById(id)){
            return repository.save(user);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addUser"+id));
        }
    }
}
