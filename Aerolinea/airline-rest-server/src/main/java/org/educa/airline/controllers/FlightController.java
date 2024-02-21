package org.educa.airline.controllers;

import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.educa.airline.dto.FlightDTO;
import org.educa.airline.entity.Flight;
import org.educa.airline.mappers.FlightMapper;
import org.educa.airline.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService,
                            FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<FlightDTO>> findAllFlight(@RequestParam(value = "ori") String origin, @RequestParam(value = "des") String destination) {
        List<FlightDTO> flightDTOs = flightMapper.toDTOs(
                flightService.findAllFlight(origin, destination));
        return ResponseEntity.ok(flightDTOs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FlightDTO> findFlightByID(@PathVariable("id") String id, @RequestParam(value = "date") String date) {
        FlightDTO flightDTO = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCasted = castStringDate(date, formato);
        try {
            flightDTO = flightMapper.toDTO(
                    flightService.findFlightByIdDate(id, dateCasted));
            return ResponseEntity.ok(flightDTO);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    private static Date castStringDate(String date, SimpleDateFormat formato) {
        try {
            Date fechaDate = formato.parse(date);
            return fechaDate;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping(path = "/create")
    public ResponseEntity<Void> create(@RequestBody @Valid FlightDTO flightDTO) {
        try {
            Flight flight = flightMapper.toEntity(flightDTO);
            if (flightService.create(flight)) {
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid FlightDTO flightDTO,
                                       @PathVariable("id") String id) {
        Flight flight = flightMapper.toEntity(flightDTO);
        try {
            if (flightService.update(flight, id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            if (
                    flightService.delete(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
