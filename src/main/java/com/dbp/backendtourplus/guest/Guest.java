package com.dbp.backendtourplus.guest;

import com.dbp.backendtourplus.booking.domain.Booking;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "identification_number")
    private String identificationNumber;

    @ManyToOne
    private Booking booking;
}
