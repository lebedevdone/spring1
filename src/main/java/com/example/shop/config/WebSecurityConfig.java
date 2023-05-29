package com.example.shop.config;

import com.example.shop.service.userDetails.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig   {

    private final UserDetailsServiceImpl userDetailsService;
    private final SuccessUserHandler successUserHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/account/**").authenticated().and()
                .authorizeHttpRequests()
                .requestMatchers("/v1/**").hasRole("ADMIN").and()
                .authorizeHttpRequests()
                .requestMatchers("/v2/**").permitAll().and()
                .authorizeHttpRequests()
                .requestMatchers("/cart/**").authenticated().and()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN").and()
                .authorizeHttpRequests().and()
                .formLogin().loginPage("/auth").loginProcessingUrl("/authenticate").successHandler(successUserHandler).permitAll().and()
                .logout().logoutUrl("/logout").permitAll().and()
                .authorizeHttpRequests()
                .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
      return userDetailsService;
    }

}
