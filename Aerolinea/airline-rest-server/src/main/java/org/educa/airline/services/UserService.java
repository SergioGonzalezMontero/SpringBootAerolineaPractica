package org.educa.airline.services;


import org.educa.airline.Exceptions.NotExistUser;
import org.educa.airline.entity.User;

import org.educa.airline.repository.inmemory.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final InMemoryUserRepository inMemoryUserRepository;



    @Autowired
    public UserService(InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;

    }


    public boolean create(User user) throws NotExistUser {
        inMemoryUserRepository.createUser(user);
        return true;
    }

    public boolean update(User user, String id, String nif) {

        if (inMemoryUserRepository.existUser(id)) {
            inMemoryUserRepository.updateUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String username, String nif) {
        return inMemoryUserRepository.deleteUser(username);
    }

    public List<User> findAllUser() throws NotExistUser {
            return inMemoryUserRepository.getUsers();
    }


    public boolean exitPassenger(String username) {
        return inMemoryUserRepository.existUser(username);
    }

    public User findUser(String email) {
        return inMemoryUserRepository.getUser(email);
    }
}
