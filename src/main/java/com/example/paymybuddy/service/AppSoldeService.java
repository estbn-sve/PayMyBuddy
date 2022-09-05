package com.example.paymybuddy.service;

import com.example.paymybuddy.model.AppSolde;
import com.example.paymybuddy.repository.AppSoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppSoldeService {
    @Autowired
    private AppSoldeRepository repository;

    public AppSolde getAppSolde(){
        List<AppSolde> appSoldeList = repository.findAll();
        if (appSoldeList.size()==0){
            throw new IllegalStateException();
        }
        return appSoldeList.get(0);
    }

    public void initAppSolde(){
        AppSolde appSolde = new AppSolde();
        appSolde.setSolde(0.00);
        repository.save(appSolde);
    }

    public AppSolde addCommision(Double commison){
        AppSolde appSolde = getAppSolde();
        appSolde.setSolde(appSolde.getSolde()+commison);
        return repository.save(appSolde);
    }
}
