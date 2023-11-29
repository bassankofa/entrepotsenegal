package com.entrepot.senegal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.entrepot.senegal.model.User;
import com.entrepot.senegal.repository.UserRepository;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.LongSerializer;

import lombok.Data;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(final Long id) {
        return userRepository.findById(id);
    }
  
    public List<User> getAllUser() {
        List<User> user= userRepository.findAll();

        return user;
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User data) {

       // List<GrantedAuthority> aut=new ArrayList<GrantedAuthority>(10);

        //User user;
        data.setPassword(data.getPassword());

       // data.setAuthorities(aut);
        User saveUser = userRepository.save(data);



        return saveUser;

    }
    public User getUserByLogin(String login){

        User user=userRepository.findByLogin(login);

        return user;
    }
}
