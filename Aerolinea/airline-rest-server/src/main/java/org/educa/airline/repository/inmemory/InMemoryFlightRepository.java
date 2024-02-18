package org.educa.airline.repository.inmemory;

import org.educa.airline.entity.Flight;
import org.educa.airline.repository.FlightRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InMemoryFlightRepository implements FlightRepository {
    private Map<String, Flight> flights = new HashMap<>();

    public InMemoryFlightRepository() {
        Flight flight = new Flight();
        flight.setId("12024-01-12");
        flight.setCode("1");
        flight.setOrigin("Madrid");
        flight.setDestination("Barcelona");
        flight.setDate(new Date(2024-1990, 1, 12));
        flights.put(flight.getId(), flight);
    }

    @Override
    public List<Flight> list(String origin, String destination) {
        return
                flights
                        .values()
                        .stream()
                        .filter(f -> origin == null || origin.equals(f.getOrigin()))
                        .filter(f -> destination == null || destination.equals(f.getDestination()))
                        .collect(Collectors.toList());
    }

    @Override
    public Flight getFlight(String flightId) {
        return flights.get(flightId);
    }

    @Override
    public boolean add(Flight flight) {
        if (!flights.containsKey(flight.getId())) {
            flights.put(flight.getId(), flight);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateFlight(
            String flightId,
            Flight flight
    ) {
        // 3 -> Madrid - Londres
        // 4 -> 1 Madrid - Berlin
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            flights.put(flight.getId(), flight);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String flightId) {
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            return true;
        } else {
            return false;
        }
    }
}
