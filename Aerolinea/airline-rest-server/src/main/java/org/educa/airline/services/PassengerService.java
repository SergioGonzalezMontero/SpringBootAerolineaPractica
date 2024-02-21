package org.educa.airline.services;

import org.educa.airline.entity.Flight;
import org.educa.airline.entity.Passenger;
import org.educa.airline.repository.inmemory.InMemoryFlightRepository;
import org.educa.airline.repository.inmemory.InMemoryPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PassengerService {
    private final InMemoryPassengerRepository inMemoryPassengerRepository;
    private final FlightService flightService;
    @Autowired
    public PassengerService(InMemoryPassengerRepository inMemoryPassengerRepository, FlightService flightService) {
        this.inMemoryPassengerRepository = inMemoryPassengerRepository;
        this.flightService = flightService;
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
    public boolean create(String id, Passenger passenger){
        return inMemoryPassengerRepository.addPassenger(passenger);
    }
    public boolean update(Passenger passenger, String id, String nif) throws Exception {

         if(inMemoryPassengerRepository.existPassenger(id,nif)){
             inMemoryPassengerRepository.updatePassenger(nif, passenger);
             return true;
         }else{
             return false;
         }
    }

    public boolean delete(String id, String nif) {
        return inMemoryPassengerRepository.deletePassenger(id, nif);
    }

    public List<Passenger> findAllPassangerOfFlight(String id){
        return inMemoryPassengerRepository.listPassengers(id);
    }
    public void passengerGetFlight(String code, Date date, Passenger passenger) throws Exception {
        Flight fligthFind = flightService.findFlightByIdDate(code,date);
        passenger.setFlightId(fligthFind.getId());
    }
    public boolean exitPassenger(String id, String nif){
        return inMemoryPassengerRepository.existPassenger(id,nif);
    }

    public Passenger findOnePassanger(String id, String nif) {
        return inMemoryPassengerRepository.getPassenger(id, nif);
    }
}
