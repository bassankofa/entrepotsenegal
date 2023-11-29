package com.entrepot.senegal.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.entrepot.senegal.model.User;
import com.entrepot.senegal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    private UserRepository repository;

    private PasswordEncoder encoder;

    public UserAuthenticationProvider(UserRepository repository, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.repository = repository;
    }

    /**
     * Get the username and password from authentication object and validate with password encoders matching method
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
      
        User User = repository.findByLogin(username);
        
        if (User == null) {

            throw new BadCredentialsException("Details not found");
        }

        if (encoder.matches(password, User.getPassword())) {
            
            logger.info("Successfully Authenticated the user");
            return new UsernamePasswordAuthenticationToken(username, password, getUserRoles(User.getRole()));
        } else {

             
            throw new BadCredentialsException("Password mismatch");
        }
    }

    /**
     * An user can have more than one roles separated by ",". We are splitting each role separately
     * @param UserRoles
     * @return
     */
    private List<GrantedAuthority> getUserRoles(String UserRoles) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String[] roles = UserRoles.split(",");
        for (String role : roles) {
            logger.info("Role: " + role);
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
        }

        return grantedAuthorityList;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}