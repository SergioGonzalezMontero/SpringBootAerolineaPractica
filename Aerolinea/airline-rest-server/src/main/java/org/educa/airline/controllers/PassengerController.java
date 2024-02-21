package org.educa.airline.controllers;

import jakarta.validation.Valid;
import org.educa.airline.dto.FlightDTO;
import org.educa.airline.dto.PassengerDTO;
import org.educa.airline.entity.Flight;
import org.educa.airline.entity.Passenger;
import org.educa.airline.mappers.PassengerMapper;
import org.educa.airline.services.FlightService;
import org.educa.airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("crear 1");
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        System.out.println("crear 2");
        passengerService.create(id, passenger);
        System.out.println("crear 3");
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
    ////////////////7
    /////////////// repasar
    ///////////////
    @GetMapping(path = "/passenger/{nif}")
    public ResponseEntity<Void> existPassenger(@PathVariable("id") String id, @PathVariable("nif") String nif){

        try{
            if(passengerService.exitPassenger(id, nif)){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(path = "/flights/{id}/passenger/{nif}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable("id")String id,@RequestParam(value = "date")String date, @PathVariable("nif") String nif, @RequestBody PassengerDTO passengerDTO) {
        // Buscar el vuelo por id
        FlightService flightService = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCasted = castStringDate(date, formato);
        Flight flight = null;
        try {
            flight = flightService.findFlightByIdDate(id, dateCasted);
            // Buscar el pasajero por nif en el vuelo
            Passenger passenger = null;
            for (Passenger p : flight.getId("id")) {
                if (p.getNif().equals(nif)) {
                    passenger = p;
                    break;
                }
            }

            if (passenger == null) {
                return ResponseEntity.notFound().build();
            }


            if(!"".equals(passenger.getNif())) {
                passenger.setNif(passengerDTO.getNif());
            }
            // Actualizar los datos del pasajero
            if(!"".equals(passenger.getName())) {
                passenger.setName(passengerDTO.getName());
            }

            if(!"".equals(passenger.getSurname())) {
                passenger.setSurname(passengerDTO.getSurname());
            }

            if(!"".equals(passenger.getEmail())) {
                passenger.setEmail(passengerDTO.getEmail());
            }
            if(0!=(passenger.getSeatNumber())) {
                passenger.setSeatNumber(passengerDTO.getSeatNumber()););
            }


            // Devolver el pasajero actualizado
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }



    }

  /*  @GetMapping(path = "/findAll/{origin}&{destination}")
    public ResponseEntity<List<PassengerDTO>> findAllFlight(@PathVariable("origin")String origin, @PathVariable("destination")String destination){
        List<PassengerDTO> passengerDTOs = passengerMapper.toDTOs(
                passengerService.findAllFl(origin, destination));
        return ResponseEntity.ok(passengerDTOs);
    }*/



  /*  @GetMapping(path= "/findByDate/{id}&{date}")
    public ResponseEntity<PassengerDTO> findFlightByID(@PathVariable("id")String id,@PathVariable("date")String date){
        PassengerDTO passengerDTO = null;

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCasted = castStringDate(date, formato);
        try{
            passengerDTO = passengerMapper.toDTO(
                    passengerService.findFlightByIdDate(id,dateCasted));
            return ResponseEntity.ok(passengerDTO);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
    }*/

    private Date castStringDate(String date, SimpleDateFormat formato) {
        try {
            Date fechaDate = formato.parse(date);
            return fechaDate;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



    @PutMapping(path = "/deleteflight/")
    public ResponseEntity<Void> update(@RequestBody @Valid PassengerDTO passengerDTO,
                                       @PathVariable("id") String id){
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        try{
            passengerService.update(passenger, id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/")
    public ResponseEntity <Void> delete(@PathVariable("id") String id){
        try{
            passengerService.delete(id);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

   /* @GetMapping(path=": /{idFlight}/passenger/{nif}")
    public ResponseEntity <Void> checkPassengerFlight(@PathVariable("idFlight") String idFlight, @PathVariable("nif") String nif){
        Date date = new Date();
        try{
            passengerService.passengerGetFlight(idFlight,nif);
            if (passengerService!= null){
                return  ResponseEntity.ok().build();
            }
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }*/


}

