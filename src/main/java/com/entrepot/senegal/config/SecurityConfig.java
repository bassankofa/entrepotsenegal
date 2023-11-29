package com.entrepot.senegal.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
   MyBasicAuthenticationEntryPoint authenticationEntryPoint;

   public final static String[] ENDPOINTS_WHITELIST={
    "alluser","save"
   };

   // public final DatabaseUserDetailsService databaseUserDetailsService;
    //private final DatabaseUserDetailPasswordService databaseUserDetailPasswordService;
 

     /* public SecurityConfig(DatabaseUserDetailsService databaseUserDetailsService,
                          DatabaseUserDetailPasswordService databaseUserDetailPasswordService ) {
              this.databaseUserDetailsService = databaseUserDetailsService;
              this.databaseUserDetailPasswordService=databaseUserDetailPasswordService;
  }
   @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder){


         UserDetails user=User.withUsername("user")
        .password(passwordEncoder.encode("passer123"))
        .roles("USER")
        .build();

        UserDetails admin =User.withUsername("admin")
        .password(passwordEncoder.encode("admin"))
        .roles("USER","ADMIN")
        .build();

        return new InMemoryUserDetailsManager(user,admin);

    }
*/
  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

           http
            .csrf().disable()
            .authorizeHttpRequests(request->
                 request.requestMatchers("/save").permitAll()
               .requestMatchers("/info").permitAll()
                .requestMatchers("/alluser").hasAuthority("USER")
                 .anyRequest().authenticated()
                
            )
     
            .httpBasic();
          // .authenticationEntryPoint(authenticationEntryPoint);

          /*  http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);*/

        return http.build();
                
                


    }

   

    @Bean
    public PasswordEncoder passwordEncoder( ){
     // we must use deprecated encoder to support their encoding
   

     return  new BCryptPasswordEncoder();
        //PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
    /*
  @Bean
  public AuthenticationProvider daoAuthenticationProvider() {

    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

         System.out.print(("Bonjour"));
        //  provider.setPasswordEncoder(passwordEncoder());
          provider.setUserDetailsPasswordService(this.databaseUserDetailPasswordService);
          provider.setUserDetailsService(this.databaseUserDetailsService);



      return provider;
  }*/
}
