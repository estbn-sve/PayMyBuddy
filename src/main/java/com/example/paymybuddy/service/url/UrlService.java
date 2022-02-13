package com.example.paymybuddy.service.url;

import com.example.paymybuddy.controller.dto.*;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
//import com.example.paymybuddy.model.UserContact;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.TransactionsRepository;
//import com.example.paymybuddy.repository.UserContactRepository;
import com.example.paymybuddy.repository.UsersRepository;
import com.example.paymybuddy.service.TransactionService;
import com.example.paymybuddy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UrlService {
    @Autowired
    private UsersRepository usersRepository;
//    @Autowired
//    private UserContactRepository userContactRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionsRepository transactionsRepository;

    public Transaction newTransactionService(final MoneyTransfertRequest moneyTransfertRequest){
        Date date = new Date();
        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setSoldeTo(moneyTransfertRequest.solde_to);
        transaction.setSoldeApp(moneyTransfertRequest.solde_app);
        transaction.setSoldeFrom(moneyTransfertRequest.solde_from);
        transaction.setUserFrom(usersService.getUser(Integer.valueOf(moneyTransfertRequest.id_user_from)));
        transaction.setUserTo(usersService.getUser(Integer.valueOf(moneyTransfertRequest.id_user_to)));

        return transactionService.addTransactions(transaction);
    }

    //TODO DOIT RENVOYER UNE LISTE de sendtransaction (boucle for each qui ajoute dans une liste)
    public Iterable<SendTransaction> getUserTransaction(final Integer id){
        ArrayList<SendTransaction> sendTransactionArrayList = new ArrayList<>();
        User user = usersService.getUser(id);

        Iterable<Transaction> transactions = transactionsRepository.findAllByUserFromOrUserTo(user, user);
        for (Transaction transaction : transactions){
            SendTransaction sendTransaction = new SendTransaction();
            sendTransaction.setDate(transaction.getDate());
            sendTransaction.setSolde_app(transaction.getSoldeApp());
            sendTransaction.setSolde_from(transaction.getSoldeFrom());
            sendTransaction.setSolde_to(transaction.getSoldeTo());
            sendTransaction.setId_user_to(transaction.getUserTo().getId());
            sendTransaction.setId_user_from(transaction.getUserFrom().getId());
            sendTransactionArrayList.add(sendTransaction);
        }

        Iterable<SendTransaction> sendTransactions = sendTransactionArrayList;

        return sendTransactions;
    }

    public User signIn (final SignInRequest signInRequest){
        UserLogin userLogin = new UserLogin();
        User user = new User();
        userLogin.setIdentifiant(signInRequest.getUser());
        userLogin.setMdp(signInRequest.getMdp());
        user.setFirstName(signInRequest.getFirstName());
        user.setLastName(signInRequest.getLastName());
        user.setEmail(signInRequest.getEmail());
        user.setUserLogin(userLogin);
        user.setIban("");
        user.setSolde(0.00);
        return usersService.addUser(user);
    }

    public List<UserContactDTO> getUserContact(final Integer id){
        List<UserContactDTO> userContactDTOList = new ArrayList<>();
        UserContactDTO userContactDTO = new UserContactDTO();
        User user = usersRepository.getById(id);
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setIban(user.getIban());
        userDTO.setSolde(user.getSolde());
        //TODO mapage USER vers USERCONTACTDTO
        //userDTO.setFriendList(user.getFriendList());

        for( User user1 : user.getFriendList()){
            userContactDTO.setId(user1.getId());
            userContactDTO.setFirstName(user1.getFirstName());
            userContactDTO.setLastName(user1.getLastName());
            userContactDTO.setEmail(user1.getEmail());
            userContactDTOList.add(userContactDTO);
        }
        userDTO.setFriendList(userContactDTOList);

        return userContactDTOList;
    }


}
