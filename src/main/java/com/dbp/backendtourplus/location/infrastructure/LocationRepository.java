package com.dbp.backendtourplus.location.infrastructure;

import com.dbp.backendtourplus.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
