package com.dbp.backendtourplus.tour.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.tour.domain.TourService;
import com.dbp.backendtourplus.tour.dto.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TourControllerTest {

    private TourController tourController;
    private TourService tourService;

    @BeforeEach
    void setUp() {
        tourService = Mockito.mock(TourService.class);
        tourController = new TourController(tourService);
    }

    @Test
    void testGetAllTours() {
        Tour tour1 = new Tour();
        Tour tour2 = new Tour();
        when(tourService.findAll()).thenReturn(Arrays.asList(tour1, tour2));

        ResponseEntity<List<Tour>> response = tourController.getAllTours();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(tourService, times(1)).findAll();
    }

    @Test
    void testGetTourById_Found() {
        Long tourId = 1L;
        Tour tour = new Tour();
        when(tourService.findById(tourId)).thenReturn(tour);

        ResponseEntity<Tour> response = tourController.getTourById(tourId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tour, response.getBody());
    }

    @Test
    void testGetTourById_NotFound() {
        Long tourId = 1L;
        when(tourService.findById(tourId)).thenThrow(new ResourceNotFoundException("Tour not found with id: " + tourId));

        assertThrows(ResourceNotFoundException.class, () -> tourController.getTourById(tourId));
    }

    @Test
    void testCreateTour() {
        TourDto tourDto = new TourDto();
        Tour createdTour = new Tour();
        when(tourService.save(tourDto)).thenReturn(createdTour);

        ResponseEntity<Tour> response = tourController.createTour(tourDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdTour, response.getBody());
        verify(tourService, times(1)).save(tourDto);
    }

    @Test
    void testUpdateTour() {
        Long tourId = 1L;
        TourDto tourDto = new TourDto();
        Tour updatedTour = new Tour();
        when(tourService.updateTour(tourId, tourDto)).thenReturn(updatedTour);

        ResponseEntity<Tour> response = tourController.updateTour(tourId, tourDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTour, response.getBody());
        verify(tourService, times(1)).updateTour(tourId, tourDto);
    }

    @Test
    void testDeleteTour() {
        Long tourId = 1L;
        doNothing().when(tourService).deleteById(tourId);

        ResponseEntity<Void> response = tourController.deleteTour(tourId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tourService, times(1)).deleteById(tourId);
    }
}
