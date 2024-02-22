package org.example.service;
import java.util.Arrays;
import java.util.Scanner;

import org.example.api.ApiPassengerService;
import org.example.dto.PassengerDTO;
import org.example.exception.BadRequestException;
import org.example.exception.NotFoundException;

public class PassengerService {

    private final ApiPassengerService apiPassengerService = new ApiPassengerService();
    public void newPassenger(Scanner scanner) {
        System.out.println("Asocia un pasajero a un vuelo");
        System.out.println("Introduce el id del vuelo:");
        String id = scanner.nextLine();
        System.out.println("Introduce el NIE del pasajero");
        String nif = scanner.nextLine();
        System.out.println("Introduce el nombre del pasajero");
        String name = scanner.nextLine();
        System.out.println("Introduce el apellido del pasajero:");
        String surname = scanner.nextLine();
        System.out.println("Introduce el email del pasajero");
        String email = scanner.nextLine();
        System.out.println("Introduce numero de asiento");
        String seatNumber = scanner.nextLine();
        int seat=stringToInt(seatNumber);
        PassengerDTO passengerDTO = new PassengerDTO(nif,id,name,surname,email,seat);

        try{
            apiPassengerService.create(passengerDTO);
            System.out.println("Pasajero creado correctamente");
        }catch (Exception e){
            System.out.println("aaah "+e.getMessage());
        }
    }

    public int stringToInt(String numberSeat){
        try {
            return Integer.parseInt(numberSeat);

        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public void checkAllPassengerOfFlight(Scanner scanner)  {
        System.out.println("Saber todos los pasajeros de un vuelo");
        System.out.println("Introduce el id del vuelo:");
        String id = scanner.nextLine();
        try {
            PassengerDTO[] passengerDTOs = apiPassengerService.findPassengersOfFlight(id);
            System.out.println(Arrays.toString(passengerDTOs));
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

    public PassengerDTO checkPassengerOfFlignt(Scanner scanner) {

        System.out.println("Introduce el id del vuelo:");
        String id = scanner.nextLine();
        System.out.println("Introduce el NIF del pasajero");
        String nif = scanner.nextLine();

        try {
            PassengerDTO passengerDTO =apiPassengerService.findPassengerOfFlight(id, nif);
            System.out.println(passengerDTO);
            return passengerDTO;
        } catch (BadRequestException e){
            System.out.println("Los parametros introducidos no son correctos");
        }catch (NotFoundException e) {
            System.out.println("Vuelo no encontrado");
        }catch (RuntimeException e){
            System.out.println("El server no está disponible");
        } catch (Exception e) {
            System.out.println("Error inesperado");
        }
        return null;
    }

    public void updatePassangerInFlight(Scanner scanner){
        System.out.println("Actualiza un pasajero en un vuelo");

        PassengerDTO passengerDTO;
        passengerDTO =checkPassengerOfFlignt(scanner);
        if(passengerDTO!= null) {
            String id = passengerDTO.getFlightId();

            String nif = passengerDTO.getNif();


            // Menú de opciones
            System.out.println("¿Qué deseas modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Email");
            System.out.println("4. Número de asiento");
            System.out.println("5. Todo");
            System.out.println("6. Volver sin modificar");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()


            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nuevo nombre:");
                    String newName = scanner.nextLine();
                    passengerDTO.setName(newName);
                    break;
                case 2:
                    System.out.println("Introduce el nuevo apellido:");
                    String newSurname = scanner.nextLine();
                    passengerDTO.setSurname(newSurname);
                    break;
                case 3:
                    System.out.println("Introduce el nuevo email:");
                    String newEmail = scanner.nextLine();
                    passengerDTO.setEmail(newEmail);
                    break;
                case 4:
                    System.out.println("Introduce el nuevo número de asiento:");
                    int newSeatNumber = scanner.nextInt();
                    passengerDTO.setSeatNumber(newSeatNumber);
                    break;

                case 5:
                    System.out.println("Introduce el nuevo nombre:");
                    String nuevoNombre = scanner.nextLine();
                    passengerDTO.setName(nuevoNombre);

                    System.out.println("Introduce el nuevo apellido:");
                    String nuevoApellido = scanner.nextLine();
                    passengerDTO.setSurname(nuevoApellido);

                    System.out.println("Introduce el nuevo email:");
                    String nuevoEmail = scanner.nextLine();
                    passengerDTO.setEmail(nuevoEmail);

                    System.out.println("Introduce el nuevo número de asiento:");
                    int nuevoNumeroAsiento = scanner.nextInt();
                    passengerDTO.setSeatNumber(nuevoNumeroAsiento);
                    break;
                case 6:
                    System.out.println("Volviendo sin modificar...");
                    return; // Salir del método sin realizar ninguna modificación
                default:
                    System.out.println("Opción no válida");
            }
            apiPassengerService.updatePassengerOfFlight(passengerDTO,id,nif);
        }else{
            System.out.println("No se ha encontrado el pasajero en el vuelo");
        }

    }

    public Boolean deletePassenger(Scanner scanner){


        PassengerDTO passengerDTO = checkPassengerOfFlignt(scanner);
        try {
            apiPassengerService.deletePassangerOfFlight(passengerDTO, passengerDTO.getFlightId(), passengerDTO.getNif());
            System.out.println("Pasajero eliminado correctamente");
            return true;
        }catch (Exception e){
            System.out.println("Algo ha fallado, no se ha realizado la eliminación");
        }

        return false;

    }
}

