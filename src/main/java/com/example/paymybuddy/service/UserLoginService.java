package com.example.paymybuddy.service;

import com.example.paymybuddy.controller.dto.UserContactDTO;
import com.example.paymybuddy.controller.dto.UserDTO;
import com.example.paymybuddy.model.User;
import com.example.paymybuddy.model.UserLogin;
import com.example.paymybuddy.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserLoginService {
    @Autowired
    private UserLoginRepository repository;

    public UserDTO getUserLogin(final String login, final String mdp){
        UserLogin userLogin = repository.findByIdentifiantAndMdp(login, mdp).orElseThrow(()->
                new NoSuchElementException("Error with getUserLogin"+login));
        UserDTO userDTO = new UserDTO();
        List<UserContactDTO> userContactDTOList = new ArrayList<>();

        userDTO.setId(userLogin.getUser().getId());
        userDTO.setFirstName(userLogin.getUser().getFirstName());
        userDTO.setLastName(userLogin.getUser().getLastName());
        userDTO.setIban(userLogin.getUser().getIban());
        userDTO.setEmail(userLogin.getUser().getEmail());
        userDTO.setSolde(userLogin.getUser().getSolde());

        for( User user1 : userLogin.getUser().getFriendList()){
            UserContactDTO userContactDTO = new UserContactDTO();
            userContactDTO.setId(user1.getId());
            userContactDTO.setFirstName(user1.getFirstName());
            userContactDTO.setLastName(user1.getLastName());
            userContactDTO.setEmail(user1.getEmail());
            userContactDTOList.add(userContactDTO);
        }

        userDTO.setFriendList(userContactDTOList);

        return userDTO;
    }

    public Iterable<UserLogin> getAllUserLogin(){
        return repository.findAll();
    }

    public List<UserLogin> addAllUserLogin(List<UserLogin> usersList){
        return repository.saveAll(usersList);
    }

    public UserLogin addUser(UserLogin userLogin){
        Integer id = userLogin.getId();
        if(!repository.existsById(id)){
            return repository.save(userLogin);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addUserLogin"+id));
        }
    }

    public UserLogin putUserLogin (UserLogin currentUserLogin,final Integer id){
        if(repository.existsById(id)){
            return currentUserLogin;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putTransaction "+id));
        }
    }
}
