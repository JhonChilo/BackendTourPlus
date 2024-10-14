package com.dbp.backendtourplus.tourcategory.infrastructure;

import com.dbp.backendtourplus.tourcategory.domain.TourCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourCategoryRepository extends JpaRepository<TourCategory, Long> {
}