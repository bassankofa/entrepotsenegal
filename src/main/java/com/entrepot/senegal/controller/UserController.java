package com.entrepot.senegal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entrepot.senegal.model.User;
import com.entrepot.senegal.service.UserService;

import jakarta.annotation.security.DeclareRoles;

@RestController
public class UserController  {

    private PasswordEncoder encoder;

    public UserController(UserService service, PasswordEncoder encoder) {
        this.userService = service;
        this.encoder = encoder;
    }


    @Autowired
     private UserService userService;


    
    @GetMapping("/alluser")
   
    public   ResponseEntity<List<User>> getAllUser(){
       
    /* Iterable<User> it=userService.getAllUser();

    List<User> users =new ArrayList<User>();

      for (User user : it) {

            users.add(user);
      } */ 
     // List<User> result = new ArrayList<User>();

      // userService.getAllUser().forEach(result::add);


     

        return ResponseEntity.ok().body(userService.getAllUser());
        //users.toString();
    }

    /**
     * @param prenom
     * @param nom
     * @param login
     * @param password
     * @param authorities
     * @return
     */
    @PostMapping("/save")
    public @ResponseBody String saveUser(@RequestBody User data) {
  User user=new User();
     //GrantedAuthority[] authorities = new GrantedAuthority[] { new SimpleGrantedAuthority("USER") };
     user.setPassword(encoder.encode(data.getPassword()));
     user.setLogin(data.getLogin());
     user.setPrenom(data.getPrenom());
     user.setNom(data.getNom());
     user.setRole(data.getRole());
     user.setLast_connect(data.getLast_connect());

    
      userService.saveUser(user);

        return "Success";
    }


   
}
