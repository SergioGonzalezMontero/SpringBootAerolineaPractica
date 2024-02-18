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

    public void checkPassengerOfFlignt(Scanner scanner) {
        System.out.println("Consulta pasajero en vuelo");
        System.out.println("Introduce el id del vuelo:");
        String id = scanner.nextLine();
        System.out.println("Introduce el NIF del pasajero");
        String nif = scanner.nextLine();

        try {
            PassengerDTO passengerDTO =apiPassengerService.findPassengerOfFlight(id, nif);
            System.out.println(passengerDTO);
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
}
