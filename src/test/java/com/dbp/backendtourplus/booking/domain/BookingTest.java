package com.dbp.backendtourplus.booking.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void testBookingCreation() {
        assertNotNull(booking.getCreatedAt(), "La fecha de creación no debería ser nula");
        assertNotNull(booking.getUpdatedAt(), "La fecha de actualización no debería ser nula");
        assertEquals(BookingStatus.PENDING, booking.getBookingStatus(), "El estado de la reserva debería ser PENDING");
        assertEquals(LocalDate.now(), booking.getBookingDate(), "La fecha de reserva debería ser hoy");
    }

    @Test
    void testBookingUpdate() throws InterruptedException {
        LocalDateTime oldUpdatedAt = booking.getUpdatedAt();
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        Thread.sleep(10);

        booking.setUpdatedAt(LocalDateTime.now());

        assertNotEquals(oldUpdatedAt, booking.getUpdatedAt(), "La fecha de actualización debería cambiar");
        assertEquals(BookingStatus.CONFIRMED, booking.getBookingStatus(), "El estado de la reserva debería ser CONFIRMED");
    }

    @Test
    void testBookingDateCannotBePast() {
        Booking pastBooking = new Booking();
        pastBooking.setBookingDate(LocalDate.now().minusDays(1));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validateBookingDate(pastBooking);
        });
        assertEquals("La fecha de reserva no puede ser en el pasado", exception.getMessage());
    }

    private void validateBookingDate(Booking booking) {
        if (booking.getBookingDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de reserva no puede ser en el pasado");
        }
    }
}
