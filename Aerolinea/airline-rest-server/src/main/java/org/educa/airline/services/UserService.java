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
public class UserService implements UserDetailsService {
    private final InMemoryUserRepository inMemoryUserRepository;


    @Autowired
    public UserService(InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;

    }


    public boolean create(User user) {
        inMemoryUserRepository.createUser(user);
        return true;
    }

    public boolean update(User user, String username) throws Exception {
        if (inMemoryUserRepository.existUser(username)) {
            if (user.getUsername().equals(username)) {
                inMemoryUserRepository.updateUser(user);
                return true;
            } else {
                if (!inMemoryUserRepository.existUser(user.getUsername())) {
                    delete(username);
                    create(user);
                    return true;
                } else {
                    return false;
                }
            }
        } else {
<<<<<<< HEAD
            throw new WhitOutPermissException();
=======
            return false;
>>>>>>> parent of 017d3d4 (repasar autorizacion en pasajeros)
        }
    }

    public boolean delete(String username) throws Exception {
<<<<<<< HEAD
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
=======

       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       UserDetails userDetail = (UserDetails) auth.getPrincipal();
>>>>>>> parent of 017d3d4 (repasar autorizacion en pasajeros)
        if (userDetail.getUsername().equals(username) || userDetail.getAuthorities().contains(new Role("ROLE_admin", "admin", "Es un administrador"))) {
            if (inMemoryUserRepository.existUser(username)) {
                inMemoryUserRepository.deleteUser(username);
                return true;
            } else {
                return false;
            }
        } else {
            throw new WhitOutPermissException();
        }
    }

    public List<User> findAllUser() throws NotExistUser {
        return inMemoryUserRepository.getUsers();
    }


    public boolean exitUser(String username) {
        return inMemoryUserRepository.existUser(username);
    }

<<<<<<< HEAD
    public User findUser(String username) throws WhitOutPermissException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        if (userDetail.getUsername().equals(username)
                || userDetail.getAuthorities().contains(new Role("ROLE_admin", "admin", "Es un administrador"))
                || userDetail.getAuthorities().contains(new Role("ROLE_personal", "personal", "Es un personal"))) {
            return inMemoryUserRepository.getUser(username);
        } else {
            throw new WhitOutPermissException();
        }

=======
    public User findUser(String username) {
        return inMemoryUserRepository.getUser(username);
>>>>>>> parent of 017d3d4 (repasar autorizacion en pasajeros)
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return inMemoryUserRepository.getUser(username);
    }

    public boolean isCurrentUser(String username) {
        // Obtiene el nombre de usuario actualmente autenticado
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        // Verifica si el usuario autenticado es el mismo que se está intentando eliminar
        return currentUsername.equals(username);
    }
}
