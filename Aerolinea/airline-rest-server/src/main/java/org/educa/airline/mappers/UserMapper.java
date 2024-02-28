package org.educa.airline.mappers;


import org.educa.airline.dto.FlightDTO;
import org.educa.airline.dto.UserDTO;
import org.educa.airline.entity.Flight;
import org.educa.airline.entity.User;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de mapear entre objetos Passenger y PassengerDTO.
 */
@Component
public class UserMapper {

    /**
     * Convierte un objeto PassengerDTO a un objeto Passenger.
     * @param userDTO Objeto PassengerDTO a convertir.
     * @return Objeto Passenger resultante.
     */

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setNif(userDTO.getNif());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    /**
     * Convierte un objeto Passenger a un objeto PassengerDTO.
     * @param user Objeto Passenger a convertir.
     * @return Objeto PassengerDTO resultante.
     */


    public UserDTO toDTO(User user)throws NullPointerException{
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setNif(user.getNif());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    /**
     * Convierte una lista de objetos Passenger a una lista de objetos PassengerDTO.
     * @param userList Lista de objetos user a convertir.
     * @return Lista de objetos PassengerDTO resultante.
     */
    public List<UserDTO> toDTOs (List<User> userList){
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user: userList){
            UserDTO usertDTO = toDTO(user);
            userDTOs.add(usertDTO);
        }
        return userDTOs;
    }
}