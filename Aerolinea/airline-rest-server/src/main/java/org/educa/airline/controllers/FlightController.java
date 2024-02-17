package org.educa.airline.controllers;

import jakarta.validation.Valid;
import org.educa.airline.dto.FlightDTO;
import org.educa.airline.entity.Flight;
import org.educa.airline.mappers.FlightMapper;
import org.educa.airline.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/flight")
public class FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService,
                            FlightMapper flightMapper){
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @GetMapping(path = "/findAll/{origin}&{destination}")
    public ResponseEntity<List<FlightDTO>> findAllFlight(@PathVariable("origin")String origin, @PathVariable("destination")String destination){
        List<FlightDTO> flightDTOs = flightMapper.toDTOs(
                flightService.findAllFlight(origin, destination));
        return ResponseEntity.ok(flightDTOs);
    }
    @GetMapping(path= "/find/{id}{fecha}")
    public ResponseEntity<FlightDTO> findFlightByID(@PathVariable("id")String id,@PathVariable("id")String fecha){
        FlightDTO flightDTO = null;
        try{
            flightDTO = flightMapper.toDTO(
                    flightService.findFlightByID(id,fecha));
            return ResponseEntity.ok(flightDTO);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path= "/findByCode/{code}")
    public ResponseEntity<FlightDTO> findFlightByCode(@PathVariable("code")String code){
        FlightDTO flightDTO = null;
        try{
            flightDTO = flightMapper.toDTO(
                    flightService.findFlightByID(code));
            return ResponseEntity.ok(flightDTO);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid FlightDTO flightDTO){
        Flight flight = flightMapper.toEntity(flightDTO);
        flightService.create(flight);
        return  ResponseEntity.status(201).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid FlightDTO flightDTO,
                                       @PathVariable("id") String id){
        Flight flight = flightMapper.toEntity(flightDTO);
        try{
            flightService.update(flight, id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable("id") String id){
        try{
            flightService.delete(id);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }


}
