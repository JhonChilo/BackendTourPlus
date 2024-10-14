package com.dbp.backendtourplus.tourinstance.infrastructure;

import com.dbp.backendtourplus.tourinstance.domain.TourInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourInstanceRepository extends JpaRepository<TourInstance, Long> {
}
