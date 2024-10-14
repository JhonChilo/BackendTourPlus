package com.dbp.backendtourplus.booking.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookingEventListener {

    @EventListener
    public void handleBookingCreatedEvent(BookingCreatedEvent event) {
        System.out.println("Booking created: " + event.getBooking());
    }
}
