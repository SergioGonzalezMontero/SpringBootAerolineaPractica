package org.educa.airline.mappers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.educa.airline.dto.FlightDTO;
import org.educa.airline.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightMapper {
    public Flight toEntity(FlightDTO flightDTO) {
    Flight flight = new Flight();
    flight.setCode(flightDTO.getCode());
    flight.setOrigin(flightDTO.getOrigin());
    flight.setDestination(flightDTO.getDestination());
    flight.setDate(stringToDate(flightDTO.getDate()));
    flight.setId(flightDTO.getId());
    return flight;

    }
    public FlightDTO toDTO(Flight flight){
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setCode(flight.getCode());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setDate(dateToString(flight.getDate()));
        flightDTO.setId(flight.getId());
        return flightDTO;
    }
    public List<FlightDTO> toDTOs (List<Flight> flightList){
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight: flightList){
            FlightDTO flightDTO = toDTO(flight);
            flightDTOs.add(flightDTO);
        }
        return flightDTOs;
    }

    public String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public Date stringToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("El formato de fecha no es válido (yyyy-MM-dd)");
            throw new RuntimeException(e);
        }
    }


}
