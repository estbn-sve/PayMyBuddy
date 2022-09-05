package com.example.paymybuddy.controller;

import com.example.paymybuddy.controller.dto.SendTransaction;
import com.example.paymybuddy.controller.dto.UserDTO;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.service.url.UrlService;
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
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlService service;

    @Test
    public void newTransactionController_Test_ShouldReturn_Ok() throws Exception {
        when(service.newTransactionService(any())).thenReturn(new SendTransaction());
        mockMvc.perform(post("/newTransaction/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void getSendTransaction_Test_ShouldReturn_Ok()throws Exception{
        when(service.getUserTransaction(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/sendTransaction/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getUserByEmail_Test_ShouldReturn_Ok()throws Exception{
        when(service.getUserByEmail(any())).thenReturn(new UserDTO());
        mockMvc.perform(get("/userEmail/email.test@email.fr"))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserContact_Test_ShouldReturn_Ok()throws Exception{
        when(service.addContact(any())).thenReturn(new User());
        mockMvc.perform(post("/addContact/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void addOut_Test_ShouldReturn_Ok()throws Exception{
        when(service.addOut(any())).thenReturn(new Transaction());
        mockMvc.perform(post("/addOut/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void sendOut_Test_ShouldReturn_Ok()throws Exception{
        when(service.sendOut(any())).thenReturn(new Transaction());
        mockMvc.perform(post("/sendOut/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }


}
