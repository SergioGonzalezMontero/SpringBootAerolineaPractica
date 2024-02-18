package org.educa.airline.services;

import org.educa.airline.entity.Flight;
import org.educa.airline.entity.Passenger;
import org.educa.airline.repository.inmemory.InMemoryPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class PassengerService {
    private final InMemoryPassengerRepository inMemoryPassengerRepository;
    private final FlightService flight;
    @Autowired
    public PassengerService(InMemoryPassengerRepository inMemoryPassengerRepository, FlightService flight) {
        this.inMemoryPassengerRepository = inMemoryPassengerRepository;
        this.flight = flight;
    }

   // public List<Passenger> findAllPassenger(Flight flight){
   //     return passengerRepository.list(flight);
    //}

    ////////////////////////////////
    ////////TODO EXCEPCION NOT FOUND
    //////////////
    /*public Passenger findPassengerByIdDate(String id, Date date) throws Exception{
     //   Passenger passenger = passengerRepository.getPassenger(id);
        Passenger passengerDate;
        for (:
             ) {

        }
      //  return passengerRepository.getPassenger(id);
    }*/
    public void create(Passenger passenger){
        inMemoryPassengerRepository.addPassenger(passenger);
    }
    public void update(Passenger passenger, String id) throws Exception {
       // passengerRepository.updatePassenger();
    }

    public void delete(String id) throws Exception {
       // passengerRepository.delete(id);
    }

    public List<Passenger> findAllPassangerOfFlight(String id){
        return inMemoryPassengerRepository.listPassengers(id);
    }
    public void passengerGetFlight(String code, Date date, Passenger passenger) throws Exception {
        Flight fligthFind = flight.findFlightByIdDate(code,date);
        passenger.setFlightId(fligthFind.getId());
    }
    public void passengerCheckFlight(){

    }
}
