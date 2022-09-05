package com.example.paymybuddy.service.url;

import com.example.paymybuddy.controller.dto.*;
import com.example.paymybuddy.model.Transaction;
import com.example.paymybuddy.model.User;
//import com.example.paymybuddy.model.UserContact;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.TransactionsRepository;
//import com.example.paymybuddy.repository.UserContactRepository;
import com.example.paymybuddy.repository.UsersRepository;
import com.example.paymybuddy.service.AppSoldeService;
import com.example.paymybuddy.service.TransactionService;
import com.example.paymybuddy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrlService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private AppSoldeService appSoldeService;

    public SendTransaction newTransactionService(final MoneyTransfertRequest moneyTransfertRequest){
        Date date = new Date();
        Transaction transaction = new Transaction();
        SendTransaction sendTransaction = new SendTransaction();
        transaction.setDate(date);
        User userTo = usersService.getUser(Integer.valueOf(moneyTransfertRequest.id_user_to));
        User userFrom = usersService.getUser(Integer.valueOf(moneyTransfertRequest.id_user_from));


        Double soldeApp = moneyTransfertRequest.solde_from*0.005;
        Double soldeFrom = moneyTransfertRequest.solde_from - soldeApp;
        Double soldeTo = moneyTransfertRequest.solde_from;
        appSoldeService.addCommision(soldeApp);

        transaction.setSoldeTo(soldeTo);
        transaction.setSoldeApp(soldeApp);
        transaction.setSoldeFrom(soldeFrom);
        transaction.setUserFrom(userFrom);
        transaction.setUserTo(userTo);

        UserDTO userDTOFrom = new UserDTO();
        UserDTO userDTOTo = new UserDTO();

        userDTOFrom.setId(transaction.getUserFrom().getId());
        userDTOFrom.setEmail(transaction.getUserFrom().getEmail());
        userDTOFrom.setFirstName(transaction.getUserFrom().getFirstName());
        userDTOFrom.setLastName(transaction.getUserFrom().getLastName());

        userDTOTo.setId(transaction.getUserTo().getId());
        userDTOTo.setEmail(transaction.getUserTo().getEmail());
        userDTOTo.setFirstName(transaction.getUserTo().getFirstName());
        userDTOTo.setLastName(transaction.getUserTo().getLastName());

        Double soldeUserTo = userTo.getSolde();
        Double soldeUserFrom = userFrom.getSolde();

        userTo.setSolde(soldeUserTo + soldeTo);
        userFrom.setSolde(soldeUserFrom - moneyTransfertRequest.getSolde_from());

        sendTransaction.setIban(transaction.getIban());
        sendTransaction.setDate(transaction.getDate());
        sendTransaction.setSolde_to(transaction.getSoldeTo());
        sendTransaction.setSolde_from(transaction.getSoldeFrom());
        sendTransaction.setSolde_app(transaction.getSoldeApp());
        sendTransaction.setId_user_from(userDTOFrom);
        sendTransaction.setId_user_to(userDTOTo);

        transactionService.addTransactions(transaction);
        return sendTransaction;
    }

    public Iterable<SendTransaction> getUserTransaction(final Integer id){
        ArrayList<SendTransaction> sendTransactionArrayList = new ArrayList<>();
        User user = usersService.getUser(id);

        Iterable<Transaction> transactions = transactionsRepository.findAllByUserFromOrUserTo(user, user);
        for (Transaction transaction : transactions){
            UserDTO userDTOFrom = new UserDTO();
            UserDTO userDTOTo = new UserDTO();

            userDTOFrom.setId(transaction.getUserFrom().getId());
            userDTOFrom.setEmail(transaction.getUserFrom().getEmail());
            userDTOFrom.setFirstName(transaction.getUserFrom().getFirstName());
            userDTOFrom.setLastName(transaction.getUserFrom().getLastName());

            userDTOTo.setId(transaction.getUserTo().getId());
            userDTOTo.setEmail(transaction.getUserTo().getEmail());
            userDTOTo.setFirstName(transaction.getUserTo().getFirstName());
            userDTOTo.setLastName(transaction.getUserTo().getLastName());

            SendTransaction sendTransaction = new SendTransaction();
            sendTransaction.setDate(transaction.getDate());
            sendTransaction.setSolde_app(transaction.getSoldeApp());
            sendTransaction.setSolde_from(transaction.getSoldeFrom());
            sendTransaction.setSolde_to(transaction.getSoldeTo());
            sendTransaction.setId_user_to(userDTOTo);
            sendTransaction.setId_user_from(userDTOFrom);
            sendTransaction.setIban(transaction.getIban());
            sendTransactionArrayList.add(sendTransaction);
        }

        return sendTransactionArrayList.stream().sorted((o1, o2) -> o1.getDate().before(o2.getDate()) ? 1 : -1).collect(Collectors.toList());
    }

    public User signIn (final SignInRequest signInRequest){
        UserLogin userLogin = new UserLogin();
        User user = new User();
        userLogin.setIdentifiant(signInRequest.getUser());
        userLogin.setMdp(signInRequest.getMdp());
        for(int i = 1 ; i<=99 ; i++){
           if(!usersRepository.existsById(i)){
               user.setId(i);
               break;
           }
        }
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
        User user = usersRepository.getById(id);

        for( User user1 : user.getFriendList()){
            UserContactDTO userContactDTO = new UserContactDTO();
            userContactDTO.setId(user1.getId());
            userContactDTO.setFirstName(user1.getFirstName());
            userContactDTO.setLastName(user1.getLastName());
            userContactDTO.setEmail(user1.getEmail());
            userContactDTOList.add(userContactDTO);
        }

        return userContactDTOList;
    }

    public UserDTO getUserByEmail(final String email){
        UserDTO userDTO = new UserDTO();
        User user = usersRepository.findByEmail(email);

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setSolde(null);
        userDTO.setIban(null);
        userDTO.setFriendList(null);
        return userDTO;
    }

    public User addContact(ContactRequest contactRequest){
        User user = usersRepository.getById(contactRequest.getId());
        List<User> newUserFriendList = user.getFriendList();
        newUserFriendList.add(usersRepository.findByEmail(contactRequest.getEmail()));
        user.setFriendList(newUserFriendList);
        usersRepository.save(user);
        return user;
    }

    public Transaction addOut(OutRequest outRequest){
        Date date = new Date();
        User user = usersRepository.getById(outRequest.getId());
        user.setSolde(user.getSolde()+ outRequest.getMontant());

        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setIban(user.getIban());
        transaction.setUserTo(user);
        transaction.setSoldeFrom(outRequest.getMontant());
        transaction.setSoldeApp(0.00);
        transaction.setSoldeTo(0.00);
        transaction.setUserFrom(usersRepository.getById(666));

        return transactionService.addTransactions(transaction);
    }

    public Transaction sendOut(OutRequest outRequest){
        Date date = new Date();
        User user = usersRepository.getById(outRequest.getId());
        user.setSolde(user.getSolde()- outRequest.getMontant());

        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setIban(user.getIban());
        transaction.setUserFrom(user);
        transaction.setSoldeTo(outRequest.getMontant());
        transaction.setSoldeApp(0.00);
        transaction.setSoldeFrom(0.00);
        transaction.setUserTo(usersRepository.getById(666));

        return transactionService.addTransactions(transaction);
    }

}
