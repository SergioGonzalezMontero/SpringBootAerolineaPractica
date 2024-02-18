package org.example.api;

import com.google.gson.Gson;
import org.example.dto.PassengerDTO;

public class ApiPassengerService extends AppiService{

    private final String URL = super.URL + "/flights";
    public void create(PassengerDTO passengerDTO) throws Exception {
        Gson gson = new Gson();
        String body = gson.toJson(passengerDTO);
        connection.doPost(body, URL+"/"+passengerDTO.getFlightId()+"/passenger");
    }
    public PassengerDTO[] findPassengersOfFlight(String id) throws Exception {
        String body = connection.doGet(URL + "/"+id+"/passengers");
        Gson gson = new Gson();
        return gson.fromJson(body, PassengerDTO[].class);
    }

    public PassengerDTO findPassengerOfFlight(String id, String nif) throws Exception {
        String body = connection.doGet(URL + "/"+id+"/passenger/"+nif);
        Gson gson = new Gson();
        return gson.fromJson(body, PassengerDTO.class);
    }
}
