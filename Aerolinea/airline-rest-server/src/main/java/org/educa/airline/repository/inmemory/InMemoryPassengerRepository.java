package org.educa.airline.repository.inmemory;

import org.educa.airline.entity.Flight;
import org.educa.airline.entity.Passenger;
import org.educa.airline.repository.PassengerRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryPassengerRepository implements PassengerRepository {
    // Por cada vuelo, habrá un mapa de pasajeros
    private final Map<String, Map<String, Passenger>> passengers = new HashMap<>();

    public InMemoryPassengerRepository() {
        Passenger passenger = new Passenger();
        passenger.setNif("12345678A");
        passenger.setFlightId("12024-01-12");
        passenger.setName("Sergio");
        passenger.setSurname("Gonzalez");
        passenger.setEmail("sergio@g.com");
        passenger.setSeatNumber(5);



    }

    @Override
    public List<Passenger> listPassengers() {
        return passengers
                .values()
                .stream()
                .flatMap(m -> m.values().stream())
                .collect(Collectors.toList());
    }

    /**
     * Dado un número de vuelo, devuelve sus pasajeros
     *
     * @param flightId Id flight
     * @return La {@link List} de {@link Passenger}
     */
    @Override
    public List<Passenger> listPassengers(String flightId) {
        return new ArrayList<>(getFlightPassengers(flightId).values());
    }

    @Override
    public Passenger getPassenger(String flightId, String nif) {
        return getFlightPassengers(flightId).get(nif);
    }

    /**
     * Dado un número de vuelo y un nif, devuelve si el pasajero está en el vuelo
     *
     * @param flightId el id del vuelo
     * @param nif      el nif del pasajero
     * @return True si existe el pasajero falso en otro caso
     */
    @Override
    public boolean existPassenger(String flightId, String nif) {
        return getFlightPassengers(flightId).containsKey(nif);
    }

    /**
     * Elimina un pasajero de un vuelo
     *
     * @param flightId el identificador del vuelo
     * @param nif      el nif del pasajero
     * @return true si se ha eliminado y
     */
    @Override
    public boolean deletePassenger(String flightId, String nif) {
        return getFlightPassengers(flightId).remove(nif) != null;
    }

    /**
     * Añada un pasajero al vuelo. Devuelve false si el pasajero ya existe en el vuelo
     *
     * @param passenger
     * @return
     */
    @Override
    public boolean addPassenger(Passenger passenger) {
        if (existPassenger(passenger.getFlightId(), passenger.getNif())) {
            return false;
        } else {
            getFlightPassengers(passenger.getFlightId()).put(passenger.getNif(), passenger);
            return true;
        }
    }

    /**
     * Actualiza los datos de un pasajero en un vuelo. Devuelve false si el
     * pasajero no existe en el vuelo
     *
     * @param passenger
     * @return
     */
    @Override
    public void updatePassenger(String nif, Passenger passenger) {
        getFlightPassengers(passenger.getFlightId()).remove(nif);
        getFlightPassengers(passenger.getFlightId()).put(passenger.getNif(), passenger);
    }

    private Map<String, Passenger> getFlightPassengers(String flightId) {
        passengers.putIfAbsent(flightId, new HashMap<>());
        return passengers.get(flightId);
    }
}
