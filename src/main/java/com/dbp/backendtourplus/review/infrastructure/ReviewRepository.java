package com.dbp.backendtourplus.review.infrastructure;

import com.dbp.backendtourplus.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
