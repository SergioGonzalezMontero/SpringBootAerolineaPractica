package org.example.service;


import org.example.api.ApiFlightService;
import org.example.dto.FlightDTO;
import org.example.dto.PassengerDTO;
import org.example.exception.BadRequestException;
import org.example.exception.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FlightService {
    private final ApiFlightService apiFlightService = new ApiFlightService();


    public void findflightOrigenDestiny(Scanner scanner) {
        System.out.println("Consulta vuelos por origen y destino");
        System.out.println("Introduce Origen");
        String origen = scanner.nextLine();
        System.out.println("Introduce Destino");
        String destino = scanner.nextLine();
        try {
            FlightDTO[] flightDTO = apiFlightService.fingFlightOrigenDestiny(origen, destino);
            for (FlightDTO flight : flightDTO) {
                printFlight(flight);
            }
        } catch (BadRequestException e) {
            System.out.println("Los parametros introducidos no son correctos");
        } catch (NotFoundException e) {
            System.out.println("Vuelo no encontrado");
        } catch (RuntimeException e) {
            System.out.println("El server no está disponible");
        } catch (Exception e) {
            System.out.println("Error inesperado");
        }
    }

    public FlightDTO findflightCodeDate(Scanner scanner) {
        System.out.println("Consulta vuelo por codigo y fecha");
        System.out.println("Introduce el código:");
        String code = scanner.nextLine();
        System.out.println("Introduce la fecha: (aaaa-mm-dd)");
        String date = scanner.nextLine();

        try {
            FlightDTO flightDTO = apiFlightService.findflightCodeDate(code, date);

            printFlight(flightDTO);
            return flightDTO;
        } catch (BadRequestException e) {
            System.out.println("La fecha no tiene un formato válido");
        } catch (NotFoundException e) {
            System.out.println("Vuelo no encontrado");
        } catch (RuntimeException e) {
            System.out.println("El server no está disponible");
        } catch (Exception e) {
            System.out.println("Error inesperado");
        }
        return null;
    }

    private static Date checkDate(SimpleDateFormat formato, String fecha) {
        try {
            Date fechaDate = formato.parse(fecha);
            return fechaDate;

        } catch (ParseException e) {
            System.out.println("Fecha no valida, recuerde formato: yyyy-MM-dd");
            return null;
        }
    }

    public void createFligth(Scanner scanner) {
        System.out.println("Crear vuelo");
        System.out.println("Introduce codigo:");
        String code = scanner.nextLine();
        System.out.println("Introduce origen:");
        String origin = scanner.nextLine();
        System.out.println("Introduce destino:");
        String destination = scanner.nextLine();
        System.out.println("Introduce la fecha: (aaaa-mm-dd)");
        String date = scanner.nextLine();
        String id = code + "-" + date;
        FlightDTO flightDTO = new FlightDTO(code, origin, destination, date, id);
        System.out.println("La ID de vuelo es " + flightDTO.getId());
        try {
            apiFlightService.create(flightDTO);
            System.out.println("Vuelo creado");
            printFlight(flightDTO);
        } catch (Exception e) {
            if (e.getClass().getName().equals("DuplicateFlightException")) {
                System.out.println("Ya existe un vuelo con ese codigo y fecha");
            } else if (e.getClass().getName().equals("BadRequestException")) {
                System.out.println("Los parametros introducidos no son correctos");
            } else if (e.getClass().getName().equals("RuntimeException")) {
                System.out.println("El server no está disponible");
            } else {
                System.out.println("Error inesperado");
            }

        }

    }

    public void deleteFlight(Scanner scanner) {

        FlightDTO flightDTO;
        try {
            flightDTO = findflightCodeDate(scanner);
            apiFlightService.delete(flightDTO.getId());
        } catch (Exception e) {
            System.out.println("No se ha realizado la eliminación");
        }
    }

    public void findAll() {
        try {
            FlightDTO[] flightDTO = apiFlightService.findAll();

            for (FlightDTO flight : flightDTO) {
                printFlight(flight);
            }

        } catch (BadRequestException e) {
            System.out.println("Los parametros introducidos no son correctos");
        } catch (NotFoundException e) {
            System.out.println("Vuelos no encontrado");
        } catch (RuntimeException e) {
            System.out.println("El server no está disponible");
        } catch (Exception e) {
            System.out.println("Error inesperado");
        }
    }

    private void printFlight(FlightDTO flightDTO) {
        ////////////////
        //////////// MAQUEAR PARA QUE QUEDE BONITO
        /////////////
        System.out.println(flightDTO);
    }

}
