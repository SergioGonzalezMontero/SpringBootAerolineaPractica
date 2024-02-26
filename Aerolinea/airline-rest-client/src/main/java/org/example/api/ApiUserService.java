package org.example.api;

import com.google.gson.Gson;
import org.example.dto.PassengerDTO;
import org.example.dto.UserDTO;

public class ApiUserService extends AppiService{

    private final String URL = super.URL + "/user";

    public void create(UserDTO userDTO) throws Exception {
        Gson gson = new Gson();
        String body = gson.toJson(userDTO);
        // Realiza una solicitud POST a la API para crear el vuelo
        connection.doPost(body, URL );
    }
    public boolean delete(String name) {
        try {
            // Realiza una solicitud DELETE a la API para eliminar el vuelo
            connection.doDelete(URL + "/" + name);
            return true;
        } catch (Exception e) {
            // Lanza una excepción RuntimeException si ocurre un error durante la solicitud
            throw new RuntimeException(e);
        }
    }
    public UserDTO updateUser(UserDTO userDTO, String name) {
        Gson gson = new Gson();
        String body = gson.toJson(userDTO);
        try {
            // Realiza una solicitud PUT a la API para actualizar los detalles del pasajero en el vuelo
            connection.doUpdate(body, URL + "/" + name);
        } catch (Exception e) {
            // Lanza una excepción RuntimeException si ocurre un error durante la solicitud
            throw new RuntimeException(e);
        }
        return gson.fromJson(body, UserDTO.class);
    }

    public UserDTO findUser(String name) throws Exception {
        String body = connection.doGet(URL + "/"+name);
        Gson gson = new Gson();
        return gson.fromJson(body,UserDTO.class);
    }
}
