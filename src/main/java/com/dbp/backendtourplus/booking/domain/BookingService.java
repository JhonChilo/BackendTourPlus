package com.dbp.backendtourplus.booking.domain;

import com.dbp.backendtourplus.booking.dto.BookingDto;
import com.dbp.backendtourplus.booking.event.BookingCreatedEvent;
import com.dbp.backendtourplus.booking.infrastructure.BookingRepository;
import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BookingService(BookingRepository bookingRepository, ApplicationEventPublisher eventPublisher) {
        this.bookingRepository = bookingRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking save(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setPerson(bookingDto.getUser());
        booking.setTourInstance(bookingDto.getTourInstance());
        booking.setBookingStatus(bookingDto.getBookingStatus());

        Booking savedBooking = bookingRepository.save(booking);

        eventPublisher.publishEvent(new BookingCreatedEvent(this, savedBooking));

        return savedBooking;
    }

    public Booking updateBooking(Long id, BookingDto bookingDto) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setPerson(bookingDto.getUser());
            existingBooking.setTourInstance(bookingDto.getTourInstance());
            existingBooking.setBookingStatus(bookingDto.getBookingStatus());
            return bookingRepository.save(existingBooking);
        }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bookingRepository.existsById(id);
    }
}

