package com.example.paymybuddy.service;

import com.example.paymybuddy.model.User;
import com.example.paymybuddy.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public User getUser(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getUser"+id));
    }

    public Iterable<User> getAllUsers(){
        return repository.findAll();
    }

    public List<User> addAllUsers(List<User> userList){
        return repository.saveAll(userList);
    }

    public User addUser(User user){
        Integer id = user.getId();
        if(!repository.existsById(id)){
            return repository.save(user);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addUser"+id));
        }
    }

    public User putUser (User currentUser , final Integer id){
        if(repository.existsById(id)){
            repository.save(currentUser);
            return currentUser;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putPerson "+id));
        }
    }

}
