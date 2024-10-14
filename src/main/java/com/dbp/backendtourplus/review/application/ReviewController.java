package com.dbp.backendtourplus.review.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.review.domain.Review;
import com.dbp.backendtourplus.review.domain.ReviewService;
import com.dbp.backendtourplus.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto) {
        Review newReview = reviewService.save(reviewDto);
        return ResponseEntity.ok(newReview);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        if (reviewService.existsById(id)) {
            Review updatedReview = reviewService.updateReview(id, reviewDto);
            return ResponseEntity.ok(updatedReview);
        } else {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (reviewService.existsById(id)) {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
    }
}
