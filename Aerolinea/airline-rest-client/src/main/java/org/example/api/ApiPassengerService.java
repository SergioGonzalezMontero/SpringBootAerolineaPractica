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

    public  PassengerDTO updatePassengerOfFlight(PassengerDTO passengerDTO, String id, String nif){

        Gson gson = new Gson();
        String body = gson.toJson(passengerDTO);
        try {
            connection.doUpdate(body, URL +"/"+id+"/passenger/"+nif);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  gson.fromJson(body, PassengerDTO.class);
    }

    public boolean deletePassangerOfFlight(PassengerDTO passengerDTO, String id, String nif){

        Gson gson = new Gson();
        String body = gson.toJson(passengerDTO);
        try {
            connection.doDelete(URL +"/"+id+"/passenger/"+nif);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
