package com.example.paymybuddy.service;

import com.example.paymybuddy.model.AppSolde;
import com.example.paymybuddy.repository.AppSoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AppSoldeService {
        @Autowired
        private AppSoldeRepository repository;

        public AppSolde getAppSolde(final Integer id){
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with getUser"+id));
        }

        public Iterable<AppSolde> getAllAppSolde(){
            return repository.findAll();
        }
}
