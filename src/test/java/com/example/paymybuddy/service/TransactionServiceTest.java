package com.example.paymybuddy.service;

import com.example.paymybuddy.controller.dto.UserContactDTO;
import com.example.paymybuddy.controller.dto.UserDTO;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.TransactionsRepository;
import com.example.paymybuddy.repository.UserLoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    TransactionsRepository repository;

    @InjectMocks
    TransactionService service;

    @Test
    public void getTransaction_Test_ShouldReturnOk(){
        Transaction transaction = new Transaction();
        when(repository.findById(any())).thenReturn(Optional.of(transaction));
        assertEquals(service.getTransactions(any()), transaction);
    }

    @Test
    public void getTransaction_Test_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getTransactions(any()));
    }

    @Test
    public void putTransaction_Test_ShouldReturnOk(){
        Transaction transaction = new Transaction();
        when(repository.existsById(any())).thenReturn(true);
//        when(repository.findById(any())).thenReturn(Optional.of(transaction));
        assertEquals(service.putTransactions(transaction,1),transaction);
    }

    @Test
    public void putTransaction_Test_shouldThrowNoSuchElement(){
        Transaction transaction = new Transaction();
        assertThrows(NoSuchElementException.class, () -> service.putTransactions(transaction,1));
    }

    @Test
    public void addTransaction_Test_ShouldReturnOk(){
        Transaction transaction = new Transaction();
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(transaction);
        assertEquals(service.addTransactions(transaction),transaction);
    }

    @Test
    public void addTransaction_Test_shouldThrowNoSuchElement(){
        Transaction transaction= new Transaction();
        when(repository.existsById(any())).thenReturn(true);
        assertThrows(NoSuchElementException.class, () -> service.addTransactions(transaction));
    }
    @Test
    public void getAllTransaction_Test_ShouldReturnOk(){
        List<Transaction> transaction = new ArrayList<>();
        when(repository.findAll()).thenReturn(transaction);
        assertEquals(service.getAllTransactions(),transaction);
    }
}
