package com.dbp.backendtourplus.tour.infrastructure;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.tourcategory.domain.TourCategory;
import com.dbp.backendtourplus.tourcategory.domain.TourCategoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class TourRepositoryTest {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Tour tour;

    @BeforeEach
    void setUp() {
        TourCategory tourCategory = new TourCategory();
        tourCategory.setName(TourCategoryStatus.NATURE);
        tourCategory.setDescription("A tour of nature.");
        entityManager.persistAndFlush(tourCategory);

        tour = new Tour();
        tour.setTitle("City Tour");
        tour.setDescription("A fun tour of the city.");
        tour.setPrice(50.0);
        tour.setTourCategory(tourCategory);

        entityManager.persistAndFlush(tour);
    }

    @Test
    public void testCreateTour() {
        Tour newTour = new Tour();
        newTour.setTitle("Beach Tour");
        newTour.setDescription("A relaxing day at the beach.");
        newTour.setPrice(75.0);

        TourCategory tourCategory = new TourCategory();
        tourCategory.setName(TourCategoryStatus.NATURE);
        entityManager.persistAndFlush(tourCategory);
        newTour.setTourCategory(tourCategory);

        Tour savedTour = tourRepository.saveAndFlush(newTour);

        Optional<Tour> createdTour = tourRepository.findById(savedTour.getId());
        assertTrue(createdTour.isPresent());
        assertEquals(newTour.getTitle(), createdTour.get().getTitle());
    }

    @Test
    public void testFindById() {
        Optional<Tour> optionalTour = tourRepository.findById(tour.getId());
        assertTrue(optionalTour.isPresent(), "El tour no se encontró");

        Tour foundTourById = optionalTour.get();
        assertEquals(tour.getId(), foundTourById.getId());
    }

    @Test
    public void testDeleteById() {
        tourRepository.deleteById(tour.getId());
        entityManager.flush();

        Optional<Tour> optionalDeletedTour = tourRepository.findById(tour.getId());
        assertFalse(optionalDeletedTour.isPresent(), "El tour no se eliminó correctamente");
    }
}