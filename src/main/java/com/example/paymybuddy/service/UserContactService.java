package com.example.paymybuddy.service;

//import com.example.paymybuddy.model.UserContact;
import com.example.paymybuddy.model.User;
//import com.example.paymybuddy.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserContactService {
//    @Autowired
//    private UserContactRepository repository;
//
//    public UserContact getUserContact(final Integer id){
//        return repository.findById(id).orElseThrow(()->
//                new NoSuchElementException("Error with getUser"+id));
//    }
//
//    public Iterable<UserContact> getAllUsers(){
//        return repository.findAll();
//    }
//
//    public List<UserContact> addAllUsers(List<UserContact> usersContactList){
//        return repository.saveAll(usersContactList);
//    }
//
//    public UserContact addUserContact(UserContact userContact){
//        Integer id = userContact.getId();
//        if(id == null || !repository.existsById(id)){
//            return repository.save(userContact);
//        } else {
//            return repository.findById(id).orElseThrow(()->
//                    new NoSuchElementException("Error with addUser"+id));
//        }
//    }
//
//    public UserContact putUserContact (UserContact currentUserContact ,final Integer id){
//        if (repository.existsById(id)){
//            UserContact userContact = currentUserContact;
//            currentUserContact = repository.findById(id).get();
//            User id_user_first = userContact.getId_user_first();
//            if (id_user_first != null) {
//                currentUserContact.setId_user_first(id_user_first);
//            }
//            User id_user_second = userContact.getId_user_second();
//            if (id_user_second !=null){
//                currentUserContact.setId_user_second(id_user_second);
//            }
//            return currentUserContact;
//        } else {
//            return repository.findById(id).orElseThrow(()->
//                    new NoSuchElementException("Error with putPerson "+id));
//        }
//    }
}
