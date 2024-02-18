package org.example.service;


import org.example.api.ApiFlightService;
import org.example.dto.FlightDTO;
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
            FlightDTO flightDTO = apiFlightService.fingFlightOrigenDestiny(origen, destino);
            System.out.println(flightDTO);
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

    public void findflightCodeDate(Scanner scanner) {
        System.out.println("Consulta vuelo por codigo y fecha");
        System.out.println("Introduce el código:");
        String code = scanner.nextLine();
        System.out.println("Introduce la fecha: (aaaa-mm-dd)");
        String date = scanner.nextLine();

        try {
            FlightDTO flightDTO = apiFlightService.findflightCodeDate(code, date);
            ///hacerlo bonito sacar metodo aparte
            System.out.println(flightDTO);
        } catch (BadRequestException e){
            System.out.println("Los parametros introducidos no son correctos");
        }catch (NotFoundException e) {
            System.out.println("Vuelo no encontrado");
        }catch (RuntimeException e){
            System.out.println("El server no está disponible");
        } catch (Exception e) {
                System.out.println("Error inesperado");
        }

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

    public void createFligth(Scanner sc) {
        System.out.println("Crear vuelo");
        System.out.println("Introduce codigo:");
        String code = sc.nextLine();
        System.out.println("Introduce origen:");
        String origin = sc.nextLine();
        System.out.println("Introduce destino:");
        String destination = sc.nextLine();
        System.out.println("Introduce la fecha: (aaaa-mm-dd)");
        String date = sc.nextLine();
        String id = code + date;
        FlightDTO flightDTO = new FlightDTO(code, origin, destination, date, id);
        System.out.println("La ID de vuelo es "+flightDTO.getId());
        try {
            apiFlightService.create(flightDTO);
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
}
