package com.example.paymybuddy.controller;

import com.example.paymybuddy.controller.dto.LoginRequest;
import com.example.paymybuddy.controller.dto.UserContactDTO;
import com.example.paymybuddy.controller.dto.UserDTO;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.service.UserLoginService;
import com.example.paymybuddy.service.UsersService;
import com.example.paymybuddy.service.url.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersService service;
    @MockBean
    private UserLoginService userLoginService;
    @MockBean
    private UrlService urlService;

    @Test
    public void getUser_Test_ShouldReturnOk() throws Exception {
        when(service.getUser(any())).thenReturn(new User());
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUser_Test_ShouldReturnNotFound() throws Exception {
        when(service.getUser(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void login_Test_ShouldReturnOk() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser("tom");
        loginRequest.setMdp("david");
        String json = new ObjectMapper().writeValueAsString(loginRequest);
        when(userLoginService.getUserLogin(any(),any())).thenReturn(new UserDTO());
        mockMvc.perform(post("/user/login")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void login_Test_ShouldReturnNotFound() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser("tom");
        loginRequest.setMdp("david");
        String json = new ObjectMapper().writeValueAsString(loginRequest);
        when(userLoginService.getUserLogin(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/user/login")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void signIn_Test_ShouldReturnOk() throws Exception {
        when(urlService.signIn(any())).thenReturn(new User());
        mockMvc.perform(post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void signIn_Test_ShouldReturnNotFound() throws Exception {
        when(urlService.signIn(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void contact_Test_ShouldReturnOk() throws Exception {
        when(urlService.getUserContact(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/user/contact/1"))
                .andExpect(status().isOk());
    }
}
