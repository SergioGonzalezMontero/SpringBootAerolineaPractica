package org.educa.airline.services;

import org.educa.airline.entity.Flight;
import org.educa.airline.repository.FlightRepository;
import org.educa.airline.repository.inmemory.InMemoryFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    private final InMemoryFlightRepository inMemoryFlightRepository;
    @Autowired
    public FlightService(InMemoryFlightRepository inMemoryFlightRepository) {
        this.inMemoryFlightRepository = inMemoryFlightRepository;
    }

    public List<Flight> findAllFlight(String origin, String destination){
        return inMemoryFlightRepository.list(origin,destination);
    }

    ////////////////////////////////
    ////////TODO EXCEPCION NOT FOUND
    //////////////
    public Flight findFlightByIdDate(String id, Date date) throws Exception{

        return inMemoryFlightRepository.getFlight(id);
    }
    public void create(Flight flight){
        inMemoryFlightRepository.add(flight);
    }
    public void update(Flight flight, String id) throws Exception {
        inMemoryFlightRepository.updateFlight(id, flight);
    }

    public void delete(String id) throws Exception {
        inMemoryFlightRepository.delete(id);
    }


}
