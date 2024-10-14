package com.dbp.backendtourplus.review.domain;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.review.dto.ReviewDto;
import com.dbp.backendtourplus.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review save(ReviewDto reviewDto) {
        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setTour(reviewDto.getTour());
        review.setAuthor(reviewDto.getAuthor());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        existingReview.setComment(reviewDto.getComment());
        existingReview.setRating(reviewDto.getRating());
        existingReview.setTour(reviewDto.getTour());
        existingReview.setAuthor(reviewDto.getAuthor());
        return reviewRepository.save(existingReview);
    }

    public void deleteById(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return reviewRepository.existsById(id);
    }
}
