package com.dbp.backendtourplus.review.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.review.domain.Review;
import com.dbp.backendtourplus.review.domain.ReviewService;
import com.dbp.backendtourplus.review.dto.ReviewDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewControllerTest {

    private ReviewController reviewController;
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        reviewService = Mockito.mock(ReviewService.class);
        reviewController = new ReviewController(reviewService);
    }

    @Test
    void testGetAllReviews() {
        Review review1 = new Review();
        Review review2 = new Review();
        when(reviewService.findAll()).thenReturn(Arrays.asList(review1, review2));

        List<Review> result = reviewController.getAllReviews();

        assertEquals(2, result.size());
        verify(reviewService, times(1)).findAll();
    }

    @Test
    void testGetReviewById_Found() {
        Long reviewId = 1L;
        Review review = new Review();
        when(reviewService.findById(reviewId)).thenReturn(Optional.of(review));

        ResponseEntity<Review> response = reviewController.getReviewById(reviewId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    void testGetReviewById_NotFound() {
        Long reviewId = 1L;
        when(reviewService.findById(reviewId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reviewController.getReviewById(reviewId));
    }

    @Test
    void testCreateReview() {
        ReviewDto reviewDto = new ReviewDto();
        Review newReview = new Review();
        when(reviewService.save(reviewDto)).thenReturn(newReview);

        ResponseEntity<Review> response = reviewController.createReview(reviewDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newReview, response.getBody());
        verify(reviewService, times(1)).save(reviewDto);
    }

    @Test
    void testUpdateReview_Found() {
        Long reviewId = 1L;
        ReviewDto reviewDto = new ReviewDto();
        Review updatedReview = new Review();
        when(reviewService.existsById(reviewId)).thenReturn(true);
        when(reviewService.updateReview(reviewId, reviewDto)).thenReturn(updatedReview);

        ResponseEntity<Review> response = reviewController.updateReview(reviewId, reviewDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReview, response.getBody());
        verify(reviewService, times(1)).updateReview(reviewId, reviewDto);
    }

    @Test
    void testUpdateReview_NotFound() {
        Long reviewId = 1L;
        ReviewDto reviewDto = new ReviewDto();
        when(reviewService.existsById(reviewId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> reviewController.updateReview(reviewId, reviewDto));
    }

    @Test
    void testDeleteReview_Found() {
        Long reviewId = 1L;
        when(reviewService.existsById(reviewId)).thenReturn(true);
        doNothing().when(reviewService).deleteById(reviewId);

        ResponseEntity<Void> response = reviewController.deleteReview(reviewId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reviewService, times(1)).deleteById(reviewId);
    }

    @Test
    void testDeleteReview_NotFound() {
        Long reviewId = 1L;
        when(reviewService.existsById(reviewId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> reviewController.deleteReview(reviewId));
    }
}
