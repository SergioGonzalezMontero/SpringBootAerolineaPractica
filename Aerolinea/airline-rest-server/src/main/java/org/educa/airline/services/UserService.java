package org.educa.airline.services;


import org.educa.airline.Exceptions.NotExistUser;
import org.educa.airline.entity.Role;
import org.educa.airline.entity.User;

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
<<<<<<< HEAD
    }}
=======
    }

    public boolean update(User user, String username) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        if (userDetail.getUsername().equals(username) || userDetail.getAuthorities().contains(new Role("ROLE_admin", "admin", "Es un administrador"))) {
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
                return false;
            }
        }else{
            System.out.println("No ha pasado la autentificacion");
            return false;
        }
    }

    public boolean delete(String username) throws Exception {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       UserDetails userDetail = (UserDetails) auth.getPrincipal();
        if (userDetail.getUsername().equals(username) || userDetail.getAuthorities().contains(new Role("ROLE_admin", "admin", "Es un administrador"))) {
            if (inMemoryUserRepository.existUser(username)) {
                inMemoryUserRepository.deleteUser(username);
                return true;
            } else {
                return false;
            }
        } else {
           throw new Exception();
        }
    }

    public List<User> findAllUser() throws NotExistUser {
        return inMemoryUserRepository.getUsers();
    }
>>>>>>> parent of 12b144b (funcional)



<<<<<<< HEAD

=======
    public User findUser(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        if (userDetail.getUsername().equals(username)
                || userDetail.getAuthorities().contains(new Role("ROLE_admin", "admin", "Es un administrador"))
                ||userDetail.getAuthorities().contains(new Role("ROLE_personal", "personal", "Es un personal"))) {
            return inMemoryUserRepository.getUser(username);
        }else{
            return null;
        }

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
>>>>>>> parent of 12b144b (funcional)
