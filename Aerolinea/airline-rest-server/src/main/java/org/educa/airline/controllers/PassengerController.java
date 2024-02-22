package org.educa.airline.controllers;

import jakarta.validation.Valid;
import org.educa.airline.Exceptions.NotExistFlight;
import org.educa.airline.Exceptions.NotExistPassenger;
import org.educa.airline.dto.PassengerDTO;

import org.educa.airline.entity.Passenger;
import org.educa.airline.mappers.PassengerMapper;
import org.educa.airline.services.PassengerService;
import org.educa.airline.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/flights/{id}")
public class PassengerController {
    private  final Tools tool;
    private final PassengerService passengerService;
    private final PassengerMapper passengerMapper;


    @Autowired
    public PassengerController(Tools tool, PassengerService passengerService,
                               PassengerMapper passengerMapper){
        this.tool = tool;
        this.passengerService = passengerService;
        this.passengerMapper = passengerMapper;
    }

    //Crear un pasajero
    @PostMapping (path = "/passenger")
    public ResponseEntity<Void> create(@PathVariable("id") String id,@RequestBody @Valid PassengerDTO passengerDTO){
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        if(tool.esDNIValido(passenger.getNif())) {
            try {
                if (passengerService.create(id, passenger)) {
                    return ResponseEntity.status(201).build();
                } else {
                    return ResponseEntity.status(409).build();
                }
            } catch (NotExistFlight e) {
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    // CHEQUEO PASAJEROS DE UN VUELO
    @GetMapping(path = "/passengers")
    public ResponseEntity<List<PassengerDTO>> findAllPassengerOfFlight(@PathVariable("id") String id){
        try {
            List<PassengerDTO> passengerDTOSs = passengerMapper.toDTOs(
                    passengerService.findAllPassangerOfFlight(id));
            return ResponseEntity.ok(passengerDTOSs);
        }catch (NotExistFlight e){
            return ResponseEntity.notFound().build();
        }

    }

    // CHEQUEO PASAJERO EN UN VUELO

    @GetMapping(path = "/passenger/{nif}")
    public ResponseEntity<PassengerDTO> existPassenger(@PathVariable("id") String id, @PathVariable("nif") String nif){

        try{
            return ResponseEntity.ok(passengerMapper.toDTO(passengerService.findOnePassanger(id, nif)));

        }catch (NotExistFlight | NotExistPassenger e){

            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(path = "/passenger/{nif}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable("id")String id, @PathVariable("nif") String nif, @RequestBody PassengerDTO passengerDTO) {

        Passenger passenger=passengerMapper.toEntity(passengerDTO);
        if(tool.esDNIValido(nif)){
            try {
                if(passengerService.update(passenger, id, nif)) {
                    return ResponseEntity.ok(passengerMapper.toDTO(passengerService.findOnePassanger(passenger.getFlightId(), passenger.getNif())));
                } else{
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/passenger/{nif}")
    public ResponseEntity <Void> deletePassenger(@PathVariable("id")String id, @PathVariable("nif") String nif){

        try {
            if(passengerService.delete(id, nif)){
                return  ResponseEntity.ok().build();
            }
        } catch (NotExistFlight | NotExistPassenger e) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.badRequest().build();
    }



}

