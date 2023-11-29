package com.entrepot.senegal.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
@Table
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;

    private String nom;

    private String login;

    private String password;

    private Date last_connect;

    private boolean enabled;

  
    private String role;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

  

   
   /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        
        

       // throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
         return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
       return true;
    }*/

}
