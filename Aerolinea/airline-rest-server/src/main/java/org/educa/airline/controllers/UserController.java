package org.educa.airline.controllers;


import jakarta.validation.Valid;
import org.educa.airline.Exceptions.NotExistUser;
import org.educa.airline.dto.UserDTO;
import org.educa.airline.entity.User;
import org.educa.airline.mappers.UserMapper;
import org.educa.airline.services.UserService;
import org.educa.airline.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping(path = "")
    public ResponseEntity<Void> create(@RequestBody @Valid UserDTO userDTO) throws NotExistUser {
        User user = userMapper.toEntity(userDTO);

        if (userService.create(user)) {
            return ResponseEntity.status(201).build(); // Si se crea correctamente, devuelve 201 Created
        } else {
            return ResponseEntity.status(409).build(); // Si ya existe un pasajero con ese DNI en el vuelo, devuelve 409 Conflict
        }

    }


    /**
     * Verifica si un pasajero existe en un vuelo.
     *
     * @param username ID del usuario.
     * @return ResponseEntity con los detalles del pasajero si existe.
     */
    @GetMapping(path = "/{username}")
    public Boolean existUser(@PathVariable("username") String username) {

        // Intenta encontrar el pasajero en el vuelo dado
        return userService.exitPassenger(username);


    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<UserDTO> findUser(@PathVariable("email") String email) {


        return ResponseEntity.ok(userMapper.toDTO(userService.findUser(email)));


    }

    /**
     * Actualiza los detalles de un pasajero en un vuelo.
     *
     * @param username nombre usuario del usuario.
     * @param userDTO  Nuevos detalles del pasajero.
     * @return ResponseEntity con los detalles del pasajero actualizados.
     */
    @PutMapping(path = "/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("username") String username, @RequestBody UserDTO userDTO) {

        User user = userMapper.toEntity(userDTO);
        // Verifica si el DNI del pasajero es válido
        if (!username.isEmpty()) {
            try {
                // Intenta actualizar los detalles del pasajero en el vuelo dado
                if (userService.update(user, username, user.getNif())) {
                    // Si se actualiza correctamente, devuelve los detalles actualizados del usuario
                    return ResponseEntity.ok(userMapper.toDTO(userService.findUser(user.getEmail())));
                } else {
                    return ResponseEntity.notFound().build(); // Si el pasajero no existe en el vuelo, devuelve 404 Not Found
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().build(); // Si ocurre algún otro error, devuelve 400 Bad Request
            }
        } else {
            return ResponseEntity.badRequest().build(); // Si el nombre no es válido, devuelve 400 Bad Request
        }
    }

    /**
     * Elimina un pasajero de un vuelo.
     *
     * @param id  ID del vuelo.
     * @param nif DNI del pasajero.
     * @return ResponseEntity con el resultado de la operación.
     */
    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String id, @PathVariable("nif") String nif) {


        // Intenta eliminar al pasajero del vuelo
        if (userService.delete(id, nif)) {
            return ResponseEntity.ok().build(); // Si se elimina correctamente, devuelve 200 OK
        }

        return ResponseEntity.badRequest().build(); // Si ocurre algún otro error, devuelve 400 Bad Request
    }
}

