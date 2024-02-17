package org.educa.airline.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PassengerDTO {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d{8}[A-Za-z]", message = "El NIF debe tener un formato válido")
    private String nif;

    @NotNull
    @NotEmpty
    private String flightId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Positive
    private int seatNumber;
}
