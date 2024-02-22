package org.example.core;

import org.example.service.FlightService;
import org.example.service.PassengerService;

import java.util.Scanner;

public class Client {

    FlightService flightService = new FlightService();
    PassengerService passengerService = new PassengerService();

    public void run() throws Exception {
        String opt = "";
        try (Scanner scanner = new Scanner(System.in)) {
            while (!"0".equals(opt)) {
                printMenu();
                opt = scanner.nextLine();
                switch (opt) {
                    case "0":
                        System.out.println("Gracias por usar la aplicación");
                        break;
                    case "1": {
                        menuFlight(scanner);
                        break;
                    }
                    case "2": {
                        menuPassenger(scanner);
                        break;
                    }
                    default:
                        System.err.println("opción no valida");
                }
            }
        }
    }

    private void menuFlight(Scanner scanner) {
        String opt;
        do {
            printMenuFlight();
            opt = scanner.nextLine();
            switch (opt) {
                case "0":
                    System.out.println("Vuelves a la pantalla principal");
                    break;
                case "1":
                    flightService.createFligth(scanner);
                    break;
                case "2":
                    flightService.findflightOrigenDestiny(scanner);
                    break;
                case "3":
                    flightService.findflightCodeDate(scanner);
                    break;
                case "4":
                    flightService.deleteFlight(scanner);
                    break;
                case "5":
                    flightService.findAll();
                    break;

            }
        } while (!"0".equals(opt));

    }

    private void menuPassenger(Scanner scanner) throws Exception {
        String opt;
        do {
            printMenuPassenger();
            opt = scanner.nextLine();
            switch (opt) {
                case "0":
                    System.out.println("Vuelves a la pantalla principal");
                    break;
                case "1": {
                    passengerService.newPassenger(scanner);
                    break;
                }
                case "2": {
                    passengerService.checkPassengerOfFlignt(scanner);

                    break;
                }
                case "3": {
                    passengerService.updatePassangerInFlight(scanner);
                    break;
                }
                case "4": {
                    passengerService.deletePassenger(scanner);
                    break;
                }
                case "5": {
                    passengerService.checkAllPassengerOfFlight(scanner);
                    break;
                }

            }
        } while (!"0".equals(opt));

    }

    private void printMenu() {
        System.out.println("Bienvenido a la app");
        System.out.println("0. Salir");
        System.out.println("1. Menú de vuelos");
        System.out.println("2. Menu de pasajeros");
        System.out.println("Introduzca una opción");
    }

    private void printMenuFlight() {
        System.out.println("Zona de vuelos");
        System.out.println("0. Volver");
        System.out.println("1. Nuevo vuelo");
        System.out.println("2. Consulta vuelos por origen y destino");
        System.out.println("3. Consulta vuelo por id y fecha");
        System.out.println("4. Elimina vuelo por id");
        System.out.println("5. Mostrar todos los vuelos");
        System.out.println("Introduzca una opción");
    }

    private void printMenuPassenger() {
        System.out.println("Zona de pasajeros");
        System.out.println("0. Volver");
        System.out.println("1. Asocia un pasajero a un vuelo");
        System.out.println("2. Consulta un si un pasajero está en un vuelo");
        System.out.println("3. Acutaliza un pasajero en un vuelo");
        System.out.println("4. Eliminar un pasajero en un vuelo");
        System.out.println("5. Consulta todos los pasajeros de un vuelo");
        System.out.println("Introduzca una opción");

    }
}
