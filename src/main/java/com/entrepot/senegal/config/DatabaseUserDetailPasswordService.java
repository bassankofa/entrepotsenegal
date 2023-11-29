package com.entrepot.senegal.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

import com.entrepot.senegal.model.User;
import com.entrepot.senegal.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class DatabaseUserDetailPasswordService implements UserDetailsPasswordService {

  private final UserRepository userRepository;

  private final UserDetailsMapper userDetailsMapper;

  public DatabaseUserDetailPasswordService(
      UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
    this.userRepository = userRepository;
    this.userDetailsMapper = userDetailsMapper;
  }

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    User userCredentials = userRepository.findByLogin(user.getUsername());
    userCredentials.setPassword(newPassword);
    return userDetailsMapper.toUserDetails(userCredentials);
  }
}