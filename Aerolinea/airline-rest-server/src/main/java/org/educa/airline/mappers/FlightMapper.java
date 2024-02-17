package org.educa.airline.mappers;


import org.educa.airline.dto.FlightDTO;
import org.educa.airline.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightMapper {
    public Flight toEntity(FlightDTO flightDTO) {
    Flight flight = new Flight();
    flight.setId(flightDTO.getId());
    flight.setOrigin(flightDTO.getOrigin());
    flight.setDestination(flightDTO.getDestination());
    flight.setDate(flightDTO.getDate());
    return flight;

    }
    public FlightDTO toDTO(Flight flight){
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setDate(flight.getDate());
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



}
