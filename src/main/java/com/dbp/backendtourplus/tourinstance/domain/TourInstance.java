package com.dbp.backendtourplus.tourinstance.domain;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.booking.domain.Booking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class TourInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LanguageStatus languageStatus;

    private Double tourDuration;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "tourInstance")
    private List<Booking> bookings;
}
