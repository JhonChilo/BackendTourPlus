package com.dbp.backendtourplus.tourinstance.domain;

import com.dbp.backendtourplus.tour.domain.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TourInstanceTest {

    private TourInstance tourInstance;
    private Tour tour;

    @BeforeEach
    void setUp() {

        tour = new Tour();
        tour.setTitle("Cultural City Tour");
        tour.setDescription("Explore the history of the city.");
        tour.setPrice(50.0);

        tourInstance = new TourInstance();
        tourInstance.setTour(tour);
        tourInstance.setLanguageStatus(LanguageStatus.ENGLISH);
        tourInstance.setTourDuration(3.5);
        tourInstance.setStartDate(LocalDateTime.of(2024, 10, 15, 9, 0));
        tourInstance.setEndDate(LocalDateTime.of(2024, 10, 15, 12, 30));
        tourInstance.setBookings(new ArrayList<>());
    }

    @Test
    void testTourInstanceLanguageStatus() {
        assertEquals(LanguageStatus.ENGLISH, tourInstance.getLanguageStatus(), "El estado del idioma debería ser 'ENGLISH'");
    }

    @Test
    void testTourInstanceDuration() {
        assertEquals(3.5, tourInstance.getTourDuration(), "La duración del tour debería ser 3.5 horas");
    }

    @Test
    void testTourInstanceDateRange() {
        assertEquals(LocalDateTime.of(2024, 10, 15, 9, 0), tourInstance.getStartDate(), "La fecha de inicio debería ser correcta");
        assertEquals(LocalDateTime.of(2024, 10, 15, 12, 30), tourInstance.getEndDate(), "La fecha de fin debería ser correcta");
    }

    @Test
    void testTourAssociation() {
        assertEquals(tour, tourInstance.getTour(), "El tour debería estar asociado correctamente con la instancia del tour");
        assertEquals("Cultural City Tour", tourInstance.getTour().getTitle(), "El título del tour asociado debería ser 'Cultural City Tour'");
    }

    @Test
    void testInitialBookingsList() {
        assertNotNull(tourInstance.getBookings(), "La lista de reservas debería inicializarse correctamente");
        assertTrue(tourInstance.getBookings().isEmpty(), "La lista de reservas debería estar vacía inicialmente");
    }
}
