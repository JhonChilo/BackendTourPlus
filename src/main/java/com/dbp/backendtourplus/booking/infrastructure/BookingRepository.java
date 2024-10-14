package com.dbp.backendtourplus.booking.infrastructure;

import com.dbp.backendtourplus.booking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
