package com.example.paymybuddy.service;

import com.example.paymybuddy.model.User;
import com.example.paymybuddy.repository.UsersRepository;
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
public class UserServiceTest {
    @Mock
    UsersRepository repository;

    @InjectMocks
    UsersService service;

    @Test
    void getUsers_Test_ShouldReturnOk(){
        User user = new User();
        when(repository.findById(any())).thenReturn(Optional.of(user));
        assertEquals(service.getUser(1), user);
    }

    @Test
    public void getUsers_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getUser(1));
    }

    @Test
    public void getAllUsers_shouldReturnOK(){
        List<User> users = new ArrayList<>();
        when(repository.findAll()).thenReturn(users);
        assertEquals(service.getAllUsers(),users);
    }
}
