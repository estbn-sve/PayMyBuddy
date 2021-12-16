package com.example.paymybuddy.service;

import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserLoginService {
    @Autowired
    private UserLoginRepository repository;

    public UserLogin getUserLogin(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getUserLogin"+id));
    }

    public Iterable<UserLogin> getAllUserLogin(){
        return repository.findAll();
    }

    public List<UserLogin> addAllUserLogin(List<UserLogin> usersList){
        return repository.saveAll(usersList);
    }

    public UserLogin addUser(UserLogin userLogin){
        Integer id = userLogin.getId();
        if(id == null || !repository.existsById(id)){
            return repository.save(userLogin);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addUserLogin"+id));
        }
    }

    public UserLogin putUserLogin (UserLogin currentUserLogin,final Integer id){
        if (repository.existsById(id)){
            UserLogin userLogin = currentUserLogin;
            currentUserLogin = repository.findById(id).get();
            String identifiant = userLogin.getIdentifiant();
            if (identifiant != null) {
                currentUserLogin.setIdentifiant(identifiant);
            }
            String mdp = userLogin.getMdp();
            if (mdp !=null){
                currentUserLogin.setMdp(mdp);
            }
            return currentUserLogin;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putPerson "+id));
        }
    }
}
