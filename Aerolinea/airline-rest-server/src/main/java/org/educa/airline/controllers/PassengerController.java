package org.educa.airline.controllers;

import jakarta.validation.Valid;

import org.educa.airline.dto.PassengerDTO;

import org.educa.airline.entity.Passenger;
import org.educa.airline.mappers.PassengerMapper;

import org.educa.airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/flights/{id}")
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerController(PassengerService passengerService,
                            PassengerMapper passengerMapper){
        this.passengerService = passengerService;
        this.passengerMapper = passengerMapper;
    }

    //HECHO CREAR FALTA PROBAR
    @PostMapping (path = "/passenger")
    public ResponseEntity<Void> create(@PathVariable("id") String id,@RequestBody @Valid PassengerDTO passengerDTO){
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passengerService.create(id, passenger);
        return  ResponseEntity.status(201).build();
    }

    // CHEQUEO PASAJEROS DE UN VUELO
    @GetMapping(path = "/passengers")
    public ResponseEntity<List<PassengerDTO>> findAllPassengerOfFlight(@PathVariable("id") String id){
        List<PassengerDTO> passengerDTOSs = passengerMapper.toDTOs(
                passengerService.findAllPassangerOfFlight(id));
        return ResponseEntity.ok(passengerDTOSs);
    }

    // CHEQUEO PASAJERO EN UN VUELO

    @GetMapping(path = "/passenger/{nif}")
    public ResponseEntity<PassengerDTO> existPassenger(@PathVariable("id") String id, @PathVariable("nif") String nif){

        try{
            if(passengerService.exitPassenger(id, nif)){
                return ResponseEntity.ok(passengerMapper.toDTO(passengerService.findOnePassanger(id, nif)));
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(path = "/passenger/{nif}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable("id")String id, @PathVariable("nif") String nif, @RequestBody PassengerDTO passengerDTO) {

        Passenger passenger=passengerMapper.toEntity(passengerDTO);

        try {
            if(passengerService.update(passenger, id, nif)) {
               return ResponseEntity.ok(passengerMapper.toDTO(passengerService.findOnePassanger(passenger.getFlightId(), passenger.getNif())));
            } else{
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping(path = "/passenger/{nif}")
    public ResponseEntity <Void> deletePassenger(@PathVariable("id")String id, @PathVariable("nif") String nif){


            if(passengerService.delete(id, nif)){
                return  ResponseEntity.ok().build();
            }else{

                return  ResponseEntity.notFound().build();
            }


    }



}

