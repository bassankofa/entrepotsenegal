package com.entrepot.senegal.config;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
class UserDetailsMapper {

  UserDetails toUserDetails(com.entrepot.senegal.model.User userCredentials) {


    return User.withUsername(userCredentials.getLogin())
        .password(userCredentials.getPassword())
        .roles(userCredentials.getRole())
        .build();
  }
}