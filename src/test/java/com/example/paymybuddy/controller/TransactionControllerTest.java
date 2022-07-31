package com.example.paymybuddy.controller;

import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService service;

    @Test
    public void getAllTransaction_Test() throws Exception {
        when(service.getAllTransactions()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/transaction"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTransaction_Test_ShouldReturn_Ok()throws Exception{
        when(service.getTransactions(any())).thenReturn(new Transaction());
        mockMvc.perform(get("/transaction/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTransaction_Test_ShouldReturn_NotFound()throws Exception{
        when(service.getTransactions(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/transaction/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addTransaction_Test_ShouldReturn_Ok()throws Exception{
        when(service.addTransactions(any())).thenReturn(new Transaction());
        mockMvc.perform(post("/transaction/").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void addTransaction_Test_ShouldReturn_NotFound()throws Exception{
        when(service.addTransactions(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/transaction/").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
}
