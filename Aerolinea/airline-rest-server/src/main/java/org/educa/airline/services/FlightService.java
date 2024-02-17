package org.educa.airline.services;

import org.educa.airline.entity.Flight;
import org.educa.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAllFlight(String origin, String destination){
        return flightRepository.list(origin,destination);
    }

    ////////////////////////////////
    ////////TODO EXCEPCION NOT FOUND
    //////////////
    public Flight findFlightByID(String id, Date date) throws Exception{
        Flight flight = flightRepository.getFlight(id);
        Flight flightDate;
        for (:
             ) {
            
        }
        return flightRepository.getFlight(id);
    }
    public void create(Flight flight){
        flightRepository.add(flight);
    }
    public void update(Flight flight, String id) throws Exception {
        flightRepository.updateFlight(id, flight);
    }

    public void delete(String id) throws Exception {
        flightRepository.delete(id);
    }
}
