package com.example.paymybuddy.service;

import com.example.paymybuddy.model.AppSolde;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.repository.AppSoldeRepository;
import com.example.paymybuddy.repository.TransactionsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppSoldeServiceTest {

    @Mock
    AppSoldeRepository repository;

    @InjectMocks
    AppSoldeService service;

    @Test
    public void addCommision(){
//        AppSolde appSolde = new AppSolde();
//        appSolde.setSolde(10.00);
//        verify(repository, times(1)).save(appSolde);
//
//        //when(repository.save(any())).thenReturn(appSolde);
//        assertEquals(service.addCommision(10.00),appSolde);
    }


    @Test
    public void initAppSolde(){

    }
}
