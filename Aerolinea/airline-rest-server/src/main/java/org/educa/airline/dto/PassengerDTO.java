package org.educa.airline.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PassengerDTO {
    @NotNull
    @NotEmpty
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
