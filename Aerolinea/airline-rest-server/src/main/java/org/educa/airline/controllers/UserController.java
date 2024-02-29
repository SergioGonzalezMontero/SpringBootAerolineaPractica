package org.educa.airline.controllers;


import jakarta.validation.Valid;
import org.educa.airline.dto.UserDTO;
import org.educa.airline.entity.User;
import org.educa.airline.exceptions.WhitOutPermissException;
import org.educa.airline.mappers.UserMapper;
import org.educa.airline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * Controlador para gestionar los pasajeros en vuelos.
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Constructor del controlador.
     *
     * @param userService Servicio para la gestión de pasajeros.
     * @param userMapper  Mapeador de pasajeros.
     */
    @Autowired
    public UserController(UserService userService,
                          UserMapper userMapper) {

        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param userDTO Datos del pasajero a crear.
     * @return ResponseEntity con el resultado de la operación.
     */



    /**
     * Verifica si un pasajero existe en un vuelo.
     *
     * @param username ID del usuario.
     * @return ResponseEntity con los detalles del pasajero si existe.
     *//*
    @GetMapping(path = "/exists/{username}")
    public ResponseEntity<Void> existUser(@PathVariable("username") String username) {
        // Intenta encontrar el pasajero en el vuelo dado
        if (userService.exitUser(username)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/


    /**
     * Actualiza los detalles de un pasajero en un vuelo.
     *
     * @param username nombre usuario del usuario.
     * @param userDTO  Nuevos detalles del pasajero.
     * @return ResponseEntity con los detalles del pasajero actualizados.
     */


    /**
     * Elimina un pasajero de un vuelo.
     *
     * @param username del usuario.
     * @return ResponseEntity con el resultado de la operación.
     */


}


