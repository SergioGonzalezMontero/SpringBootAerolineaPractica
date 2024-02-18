package org.example.api;

import com.google.gson.Gson;
import org.example.dto.FlightDTO;

import java.util.Date;

public class ApiFlightService extends AppiService{
    private final String URL = super.URL + "/flights";
    public FlightDTO fingFlightOrigenDestiny(String origen, String destino) throws Exception {
        String body = connection.doGet(URL + "?ori="+origen+"&des="+destino);
        Gson gson = new Gson();
        FlightDTO flightDTO = gson.fromJson(body, FlightDTO.class);
        return flightDTO;
    }
    public void create(FlightDTO flightDTO) throws Exception {
        Gson gson = new Gson();
        String body = gson.toJson(flightDTO);
        connection.doPost(body, URL+"/create");
    }


    public FlightDTO findflightCodeDate(String code, String date) throws Exception {
        String body = connection.doGet(URL + "/"+code+date+"?date="+date);
        Gson gson = new Gson();
        FlightDTO flightDTO = gson.fromJson(body, FlightDTO.class);
        return flightDTO;
    }
}
