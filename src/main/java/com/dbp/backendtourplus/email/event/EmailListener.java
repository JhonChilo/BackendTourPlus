package com.dbp.backendtourplus.email.event;

import com.dbp.backendtourplus.booking.event.BookingCreatedEvent;
import com.dbp.backendtourplus.email.domain.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void handleHelloEmailEvent(EmailEvent event) {
        emailService.sendMessage(event.getEmail(), event.getSubject(), event.getContent());
    }

    @EventListener
    @Async
    public void handleBookingCreatedEvent(BookingCreatedEvent event) {
        String to = event.getBooking().getPerson().getEmail();
        String subject = "Booking Confirmation";
        String content = "Thank you for your booking! Your booking ID is: " + event.getBooking().getId();

        emailService.sendMessage(to, subject, content);
    }
}
