package com.example.paymybuddy;

import com.example.paymybuddy.model.Users;
import com.example.paymybuddy.repository.UsersRepository;
import com.example.paymybuddy.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayMyBuddyApplicationTests {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Test
    void contextLoads() {
    }


    @Test
    void testConnection(){
        Users user = new Users();
        usersService.addUser(user);
        usersRepository.findById(user.getId());
    }

}
