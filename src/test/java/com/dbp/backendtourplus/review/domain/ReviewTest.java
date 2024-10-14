package com.dbp.backendtourplus.review.domain;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReviewTest {

    private Review review;
    private User author;
    private Tour tour;

    @BeforeEach
    void setUp() {
        author = new User();
        author.setEmail("author@example.com");

        tour = new Tour();
        tour.setTitle("City Tour");

        review = new Review();
        review.setAuthor(author);
        review.setComment("Amazing tour!");
        review.setRating(5);
        review.setTour(tour);
    }

    @Test
    void testReviewComment() {
        assertEquals("Amazing tour!", review.getComment(), "El comentario debería ser 'Amazing tour!'");
    }

    @Test
    void testReviewRating() {
        assertEquals(5, review.getRating(), "La calificación debería ser 5");
    }

    @Test
    void testAuthorAssociation() {
        assertEquals("author@example.com", review.getAuthor().getEmail(), "El autor debería tener el correo 'author@example.com'");
    }

    @Test
    void testTourAssociation() {
        assertEquals("City Tour", review.getTour().getTitle(), "El tour debería llamarse 'City Tour'");
    }

    @Test
    void testSetReviewProperties() {
        review.setComment("Great experience");
        review.setRating(4);

        assertEquals("Great experience", review.getComment(), "El comentario debería ser 'Great experience'");
        assertEquals(4, review.getRating(), "La calificación debería ser 4");
    }
}
