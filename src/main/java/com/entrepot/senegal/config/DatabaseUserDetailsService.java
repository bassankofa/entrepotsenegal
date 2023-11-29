package com.entrepot.senegal.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entrepot.senegal.model.User;
import com.entrepot.senegal.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  private final UserDetailsMapper userDetailsMapper;

  public DatabaseUserDetailsService(
      UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
    this.userRepository = userRepository;
    this.userDetailsMapper = userDetailsMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {
        
    User userCredentials = userRepository.findByLogin(username);
      
    
    UserDetails userDetails=userDetailsMapper.toUserDetails(userCredentials);


    return userDetails;
  }
}