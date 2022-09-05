package com.example.paymybuddy.service;

import com.example.paymybuddy.controller.dto.*;
import com.example.paymybuddy.model.AppSolde;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.TransactionsRepository;
import com.example.paymybuddy.repository.UsersRepository;
import com.example.paymybuddy.service.url.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @InjectMocks
    UrlService service;
    @Mock
    UsersRepository usersRepository;
    @Mock
    UsersService usersService;
    @Mock
    TransactionService transactionService;
    @Mock
    TransactionsRepository transactionsRepository;
    @Mock
    AppSoldeService appSoldeService;

    @Test
    public void newTransactionService_Test_ShouldReturnOk(){

        MoneyTransfertRequest moneyTransfertRequest = new MoneyTransfertRequest();
        moneyTransfertRequest.setId_user_from("2");
        moneyTransfertRequest.setId_user_to("1");
        moneyTransfertRequest.setSolde_from(10.00);

        User userTo = new User();
        userTo.setId(1);
        userTo.setSolde(10.00);

        User userFrom = new User();
        userFrom.setId(2);
        userFrom.setSolde(100.00);

        when(usersService.getUser(2)).thenReturn(userFrom);
        when(usersService.getUser(1)).thenReturn(userTo);
        when(appSoldeService.addCommision(any())).thenReturn(null);
        when(transactionService.addTransactions(any())).thenReturn(null);

        SendTransaction sendTransaction = service.newTransactionService(moneyTransfertRequest);

        verify(appSoldeService, times(1)).addCommision(0.05);
        verify(transactionService, times(1)).addTransactions(any());
        assertEquals(sendTransaction.getId_user_from().getId(), 2);
        assertEquals(sendTransaction.getId_user_to().getId(), 1);
        assertEquals(sendTransaction.getSolde_from(), 9.95);
        assertEquals(sendTransaction.getSolde_app(), 0.05);
        assertEquals(sendTransaction.getSolde_to(), 10.00);
    }

    @Test
    public void getUserTransaction_Test_ShouldReturnOk(){
        User userfrom = new User();
        userfrom.setId(1);
        User userTo = new User();
        userTo.setId(2);

        Date date = new Date();

        Transaction transaction1 = new Transaction();
        transaction1.setDate(date);
        transaction1.setId(1);
        transaction1.setSoldeFrom(10.00);
        transaction1.setUserTo(userTo);
        transaction1.setUserFrom(userfrom);

        Iterable<Transaction> transactions = List.of(transaction1);
        when(usersService.getUser(any())).thenReturn(userfrom);
        when(transactionsRepository.findAllByUserFromOrUserTo(userfrom, userfrom)).thenReturn(transactions);
        Iterable<SendTransaction> sendTransactionArrayList = service.getUserTransaction(1);
        List<SendTransaction> transactionList = new ArrayList<>();
        sendTransactionArrayList.forEach(transactionList::add);

        assertEquals(10.00,transactionList.get(0).getSolde_from());
    }


    @Test
    public void getUserContact_Test_ShouldReturnOk(){
        User user = new User();

        User userContact = new User();
        userContact.setId(1);
        userContact.setFirstName("david");
        userContact.setLastName("tom");
        userContact.setEmail("david.tom@mail.fr");

        User userContact1 = new User();
        userContact1.setId(2);
        userContact1.setFirstName("jerry");
        userContact1.setLastName("tom");
        userContact1.setEmail("jerry.tom@mail.fr");

        List<User> lu = Arrays.asList(userContact,userContact1);
        user.setFriendList(lu);

        when(usersRepository.getById(any())).thenReturn(user);
        List<UserContactDTO> userContactDTOList = service.getUserContact(1);

        assertEquals(userContactDTOList.get(0).getId(), 1);
        assertEquals(userContactDTOList.get(0).getEmail(),"david.tom@mail.fr");
        assertEquals(userContactDTOList.get(0).getLastName(),"tom");
        assertEquals(userContactDTOList.get(0).getFirstName(),"david");
        assertEquals(userContactDTOList.get(1).getId(), 2);
        assertEquals(userContactDTOList.get(1).getEmail(),"jerry.tom@mail.fr");
        assertEquals(userContactDTOList.get(1).getLastName(),"tom");
        assertEquals(userContactDTOList.get(1).getFirstName(),"jerry");
    }


    @Test
    public void addOut_Test_ShouldReturnOk(){
        OutRequest outRequest = new OutRequest();
        outRequest.setId(1);
        outRequest.setMontant(10.00);
        Date date = new Date();
        User user = new User();
        user.setSolde(100.00);
        User outUser = new User();
        outUser.setId(666);
        outUser.setSolde(50.00);
        when(usersRepository.getById(any())).thenReturn(user);
        when(usersRepository.getById(any())).thenReturn(outUser);
        user.setSolde(user.getSolde()+ outRequest.getMontant());

        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setIban(user.getIban());
        transaction.setUserTo(user);
        transaction.setSoldeFrom(outRequest.getMontant());
        transaction.setSoldeApp(0.00);
        transaction.setSoldeTo(0.00);
        transaction.setUserFrom(outUser);
        when(transactionService.addTransactions(any())).thenReturn(transaction);
        assertEquals(service.addOut(outRequest),transaction);
    }


    @Test
    public void sendOut_Test_ShouldReturnOk(){
        OutRequest outRequest = new OutRequest();
        outRequest.setId(1);
        outRequest.setMontant(10.00);
        Date date = new Date();
        User user = new User();
        user.setSolde(100.00);
        User outUser = new User();
        outUser.setId(666);
        outUser.setSolde(50.00);
        when(usersRepository.getById(any())).thenReturn(user);
        when(usersRepository.getById(any())).thenReturn(outUser);
        user.setSolde(user.getSolde()- outRequest.getMontant());

        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setIban(user.getIban());
        transaction.setUserFrom(user);
        transaction.setSoldeTo(outRequest.getMontant());
        transaction.setSoldeApp(0.00);
        transaction.setSoldeFrom(0.00);
        transaction.setUserTo(outUser);

        when(transactionService.addTransactions(any())).thenReturn(transaction);
        assertEquals(service.sendOut(outRequest),transaction);
    }


    @Test
    public void signIn_Test_ShouldReturnOk(){
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("teste@email.fr");
        signInRequest.setFirstName("david");
        signInRequest.setLastName("caroline");
        signInRequest.setMdp("123");
        signInRequest.setUser("david.caroline");

        UserLogin userLogin = new UserLogin();
        userLogin.setIdentifiant(signInRequest.getUser());
        userLogin.setMdp(signInRequest.getMdp());

        User user = new User();
        user.setFirstName(signInRequest.getFirstName());
        user.setLastName(signInRequest.getLastName());
        user.setEmail(signInRequest.getEmail());
        user.setUserLogin(userLogin);
        user.setIban("");
        user.setSolde(0.00);
        when(usersService.addUser(any())).thenReturn(user);
        assertEquals(service.signIn(signInRequest),user);
    }

    @Test
    public void getUserByEmail_Test_ShouldReturnOk(){
        UserDTO userDTO = new UserDTO();
        User user = new User();
        when(usersRepository.findByEmail(any())).thenReturn(user);

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setSolde(null);
        userDTO.setIban(null);
        userDTO.setFriendList(null);
        assertEquals(service.getUserByEmail(any()),userDTO);
    }

    @Test
    public void addContact_Test_ShouldReturnOk(){
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmail("email@test.fr");
        contactRequest.setId(1);
        User user = new User();
        when(usersRepository.getById(any())).thenReturn(user);
        List<User> newUserFriendList = new ArrayList<>();
        User addUser = new User();
        when(usersRepository.findByEmail(any())).thenReturn(addUser);
        newUserFriendList.add(addUser);
        user.setFriendList(newUserFriendList);
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(service.addContact(contactRequest),user);
    }
}
