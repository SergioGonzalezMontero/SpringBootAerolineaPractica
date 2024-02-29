package org.educa.airline.services;


import org.educa.airline.entity.Role;
import org.educa.airline.entity.User;
import org.educa.airline.exceptions.NotExistUser;
import org.educa.airline.exceptions.WhitOutPermissException;
import org.educa.airline.repository.inmemory.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final InMemoryUserRepository inMemoryUserRepository;


    @Autowired
    public UserService(InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;

    }


    public boolean create(User user) {
        inMemoryUserRepository.createUser(user);
        return true;
    }}




