package com.dbp.backendtourplus.booking.dto;

import com.dbp.backendtourplus.booking.domain.BookingStatus;
import com.dbp.backendtourplus.person.domain.Person;
import com.dbp.backendtourplus.tourinstance.domain.TourInstance;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    @NotNull(message = "La fecha de reserva no puede ser nula")
    private LocalDate bookingDate;

    @NotNull(message = "El estado de la reserva no puede ser nulo")
    private BookingStatus bookingStatus;

    @NotNull(message = "La instancia de tour no puede ser nula")
    private TourInstance tourInstance;

    @NotNull(message = "La persona no puede ser nula")
    private Person user;
}
