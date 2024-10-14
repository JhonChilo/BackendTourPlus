package com.dbp.backendtourplus.booking.application;

import com.dbp.backendtourplus.booking.domain.Booking;
import com.dbp.backendtourplus.booking.domain.BookingService;
import com.dbp.backendtourplus.booking.dto.BookingDto;
import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingControllerTest {

    private BookingController bookingController;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingService = Mockito.mock(BookingService.class);
        bookingController = new BookingController(bookingService);
    }

    @Test
    void testGetAllBookings() {

        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        when(bookingService.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        List<Booking> result = bookingController.getAllBookings();

        assertEquals(2, result.size());
        verify(bookingService, times(1)).findAll();
    }

    @Test
    void testGetBookingById_Found() {

        Long bookingId = 1L;
        Booking booking = new Booking();
        when(bookingService.findById(bookingId)).thenReturn(Optional.of(booking));

        ResponseEntity<Booking> response = bookingController.getBookingById(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(booking, response.getBody());
    }

    @Test
    void testGetBookingById_NotFound() {

        Long bookingId = 1L;
        when(bookingService.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookingController.getBookingById(bookingId));
    }

    @Test
    void testCreateBooking() {
        BookingDto bookingDto = new BookingDto();
        Booking booking = new Booking();

        when(bookingService.save(bookingDto)).thenReturn(booking);

        ResponseEntity<Booking> result = bookingController.createBooking(bookingDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(booking, result.getBody());
        verify(bookingService, times(1)).save(bookingDto);
    }


    @Test
    void testUpdateBooking_Found() {

        Long bookingId = 1L;
        BookingDto bookingDto = new BookingDto();
        Booking updatedBooking = new Booking();
        when(bookingService.existsById(bookingId)).thenReturn(true);
        when(bookingService.updateBooking(bookingId, bookingDto)).thenReturn(updatedBooking);

        ResponseEntity<Booking> response = bookingController.updateBooking(bookingId, bookingDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBooking, response.getBody());
    }

    @Test
    void testUpdateBooking_NotFound() {

        Long bookingId = 1L;
        BookingDto bookingDto = new BookingDto();
        when(bookingService.existsById(bookingId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookingController.updateBooking(bookingId, bookingDto));
    }

    @Test
    void testDeleteBooking_Found() {

        Long bookingId = 1L;
        when(bookingService.existsById(bookingId)).thenReturn(true);

        ResponseEntity<Void> response = bookingController.deleteBooking(bookingId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookingService, times(1)).deleteById(bookingId);
    }

    @Test
    void testDeleteBooking_NotFound() {

        Long bookingId = 1L;
        when(bookingService.existsById(bookingId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookingController.deleteBooking(bookingId));
    }
}
