package com.dbp.backendtourplus.booking.application;

import com.dbp.backendtourplus.booking.domain.Booking;
import com.dbp.backendtourplus.booking.domain.BookingService;
import com.dbp.backendtourplus.booking.dto.BookingDto;
import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        if (booking.isEmpty()) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        return ResponseEntity.ok(booking.get());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingDto bookingDto) {
        Booking booking = bookingService.save(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        if (bookingService.existsById(id)) {
            Booking updatedBooking = bookingService.updateBooking(id, bookingDto);
            return ResponseEntity.ok(updatedBooking);
        } else {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingService.existsById(id)) {
            bookingService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
    }

}
