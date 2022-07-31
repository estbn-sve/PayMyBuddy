package com.example.paymybuddy.service;

import com.example.paymybuddy.controller.dto.UserContactDTO;
import com.example.paymybuddy.controller.dto.UserDTO;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.UserLoginRepository;
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
public class UserLoginServiceTest {
    @Mock
    UserLoginRepository repository;

    @InjectMocks
    UserLoginService service;

    @Test
    public void getUserLogin_Test_ShouldReturnOk(){
        UserLogin userLogin = new UserLogin();
        UserDTO userDTO = new UserDTO();
        User user = new User();

        List<User> userList = new ArrayList<>();
        List<UserContactDTO> userDTOList = new ArrayList<>();

        user.setFriendList(userList);
        userLogin.setUser(user);
        userDTO.setFriendList(userDTOList);

        when(repository.findByIdentifiantAndMdp(any(),any())).thenReturn(Optional.of(userLogin));
        assertEquals(service.getUserLogin(any(),any()), userDTO);
    }

    @Test
    public void getUserLogin_Test_shouldThrowNoSuchElement(){
        when(repository.findByIdentifiantAndMdp(any(),any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getUserLogin(any(),any()));
    }

    @Test
    public void putUserLogin_Test_ShouldReturnOk(){
        UserLogin user = new UserLogin();
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(user));
        assertEquals(service.putUserLogin(user,1),user);
    }

    @Test
    public void putUserLogin_Test_shouldThrowNoSuchElement(){
        UserLogin user = new UserLogin();
        when(repository.existsById(any())).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> service.putUserLogin(user,1));
    }

    @Test
    public void addUserLogin_Test_ShouldReturnOk(){
        UserLogin user = new UserLogin();
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(user);
        assertEquals(service.addUser(user),user);
    }

    @Test
    public void addUserLogin_Test_shouldThrowNoSuchElement(){
        UserLogin user= new UserLogin();
        when(repository.existsById(any())).thenReturn(true);
        assertThrows(NoSuchElementException.class, () -> service.addUser(user));
    }
    @Test
    public void getAllUserLogin_Test_ShouldReturnOk(){
        List<UserLogin> users = new ArrayList<>();
        when(repository.findAll()).thenReturn(users);
        assertEquals(service.getAllUserLogin(),users);
    }
    @Test
    public void addAllUserLogin_Test_ShouldReturnOk(){
        List<UserLogin> lu = new ArrayList<>();
        when(repository.saveAll(any())).thenReturn(lu);
        assertEquals(service.addAllUserLogin(lu),lu);
    }
}
