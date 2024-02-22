package org.educa.airline.controllers;

import jakarta.validation.Valid;
import org.educa.airline.Exceptions.BadDateException;
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
        if(!flightDTOs.isEmpty()){
            return ResponseEntity.ok(flightDTOs);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FlightDTO> findFlightByID(@PathVariable("id") String id, @RequestParam(value = "date") String date) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateCasted = castStringDate(date, formato);
            Flight flight = flightService.findFlightByIdDate(id, dateCasted);
            if(flight != null) {
                return ResponseEntity.ok(flightMapper.toDTO(flight));
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private Date castStringDate(String date, SimpleDateFormat formato) throws BadDateException {
        try {
            Date fechaDate = formato.parse(date);
            return fechaDate;

        } catch (ParseException e) {
            throw new BadDateException();
        }
    }


    @PostMapping(path = "/create")
    public ResponseEntity<Void> create(@RequestBody @Valid FlightDTO flightDTO) {
        try {
            Flight flight = flightMapper.toEntity(flightDTO);
            if (flightService.create(flight)) {
                //creado
                return ResponseEntity.status(201).build();
            } else {
                //duplicado
                return ResponseEntity.status(409).build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    /* Se estudió hacer el vuelo
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id,
                                       @RequestBody @Valid FlightDTO flightDTO) {
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
    */
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

    @GetMapping(path = "/allflights")
    public ResponseEntity<List<FlightDTO>> getAllFlight(){
        List<FlightDTO> flightDTOs = flightMapper.toDTOs(
                flightService.findAllFlight());
        if(!flightDTOs.isEmpty()){
            return ResponseEntity.ok(flightDTOs);
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

}
