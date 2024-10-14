package com.dbp.backendtourplus.tour.infrastructure;

import com.dbp.backendtourplus.tour.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
