package com.dbp.backendtourplus.tourcategory.domain;

import com.dbp.backendtourplus.tour.domain.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TourCategoryTest {

    private TourCategory tourCategory;
    private Tour tour1;
    private Tour tour2;

    @BeforeEach
    void setUp() {

        tour1 = new Tour();
        tour1.setTitle("Cultural City Tour");
        tour1.setDescription("Explore the history of the city.");
        tour1.setPrice(50.0);

        tour2 = new Tour();
        tour2.setTitle("Adventure Mountain Hike");
        tour2.setDescription("A challenging hike through the mountains.");
        tour2.setPrice(150.0);

        tourCategory = new TourCategory();
        tourCategory.setName(TourCategoryStatus.ADVENTURE);
        tourCategory.setDescription("Tours for thrill-seekers and adventurers.");
        tourCategory.setTours(Arrays.asList(tour1, tour2));

        tour1.setTourCategory(tourCategory);
        tour2.setTourCategory(tourCategory);
    }

    @Test
    void testTourCategoryName() {
        assertEquals(TourCategoryStatus.ADVENTURE, tourCategory.getName(), "El nombre de la categoría debería ser 'ADVENTURE'");
    }

    @Test
    void testTourCategoryDescription() {
        assertEquals("Tours for thrill-seekers and adventurers.", tourCategory.getDescription(), "La descripción de la categoría debería ser correcta");
    }

    @Test
    void testTourCategoryToursAssociation() {
        assertEquals(2, tourCategory.getTours().size(), "Debería haber 2 tours asociados a la categoría");
        assertTrue(tourCategory.getTours().contains(tour1), "El tour 'Cultural City Tour' debería estar asociado a la categoría");
        assertTrue(tourCategory.getTours().contains(tour2), "El tour 'Adventure Mountain Hike' debería estar asociado a la categoría");
    }
}
