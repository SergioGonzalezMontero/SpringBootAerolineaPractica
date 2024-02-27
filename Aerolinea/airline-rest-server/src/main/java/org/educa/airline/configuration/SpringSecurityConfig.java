package org.educa.airline.configuration;

import lombok.Getter;

import org.educa.airline.services.CryptService;

import org.educa.airline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Getter
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    private final UserService userService;
    private final CryptService cryptService;

    @Autowired
    public SpringSecurityConfig(UserService userService, CryptService cryptService) {
        this.userService = userService;
        this.cryptService = cryptService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    public CryptService passwordEncoder() {
        return cryptService;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults()).authorizeHttpRequests(
                        request -> request.requestMatchers("/user").anonymous())/*
                                .requestMatchers()



                        request.requestMatchers("/crypt/crypt").anonymous()
                                .requestMatchers(HttpMethod.GET, "/crypt/test").anonymous() //esto es una prueba para comprobar el acceso discriminado por method GET
                                .requestMatchers("/crypt/decrypt").hasAnyRole("USER")
                                .requestMatchers("/crypt/hash").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/crypt/test").hasAnyRole("USER") //esto es una prueba para comprobar el acceso discriminado por method POST
                                .anyRequest().authenticated())*/;
        return http.build();
    }


}

