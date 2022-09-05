package com.example.paymybuddy;

import com.example.paymybuddy.model.AppSolde;
import com.example.paymybuddy.service.AppSoldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.SchemaOutputResolver;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

    @Autowired
    AppSoldeService appSoldeService;

    public static void main(String[] args) {
        SpringApplication.run(PayMyBuddyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            appSoldeService.getAppSolde();
        } catch (IllegalStateException e){
            appSoldeService.initAppSolde();
        }
    }
}
