package com.dbp.backendtourplus.tourinstance.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.tourinstance.domain.TourInstance;
import com.dbp.backendtourplus.tourinstance.domain.TourInstanceService;
import com.dbp.backendtourplus.tourinstance.dto.TourInstanceDto;
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
public class TourInstanceControllerTest {

    private TourInstanceController tourInstanceController;
    private TourInstanceService tourInstanceService;

    @BeforeEach
    void setUp() {
        tourInstanceService = Mockito.mock(TourInstanceService.class);
        tourInstanceController = new TourInstanceController(tourInstanceService);
    }

    @Test
    void testGetAllTourInstances() {
        TourInstance instance1 = new TourInstance();
        TourInstance instance2 = new TourInstance();
        when(tourInstanceService.findAll()).thenReturn(Arrays.asList(instance1, instance2));

        List<TourInstance> response = tourInstanceController.getAllTourInstances();

        assertEquals(2, response.size());
        verify(tourInstanceService, times(1)).findAll();
    }

    @Test
    void testGetTourInstanceById_Found() {
        Long instanceId = 1L;
        TourInstance instance = new TourInstance();
        when(tourInstanceService.findById(instanceId)).thenReturn(Optional.of(instance));

        ResponseEntity<TourInstance> response = tourInstanceController.getTourInstanceById(instanceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(instance, response.getBody());
    }

    @Test
    void testGetTourInstanceById_NotFound() {
        Long instanceId = 1L;
        when(tourInstanceService.findById(instanceId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tourInstanceController.getTourInstanceById(instanceId));
    }

    @Test
    void testCreateTourInstance() {
        TourInstanceDto tourInstanceDto = new TourInstanceDto();
        TourInstance createdInstance = new TourInstance();
        when(tourInstanceService.save(tourInstanceDto)).thenReturn(createdInstance);

        ResponseEntity<TourInstance> response = tourInstanceController.createTourInstance(tourInstanceDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdInstance, response.getBody());
        verify(tourInstanceService, times(1)).save(tourInstanceDto);
    }

    @Test
    void testUpdateTourInstance() {
        Long instanceId = 1L;
        TourInstanceDto tourInstanceDto = new TourInstanceDto();
        TourInstance updatedInstance = new TourInstance();
        when(tourInstanceService.update(instanceId, tourInstanceDto)).thenReturn(updatedInstance);

        ResponseEntity<TourInstance> response = tourInstanceController.updateTourInstance(instanceId, tourInstanceDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedInstance, response.getBody());
        verify(tourInstanceService, times(1)).update(instanceId, tourInstanceDto);
    }

    @Test
    void testDeleteTourInstance() {
        Long instanceId = 1L;
        doNothing().when(tourInstanceService).deleteById(instanceId);

        ResponseEntity<Void> response = tourInstanceController.deleteTourInstance(instanceId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tourInstanceService, times(1)).deleteById(instanceId);
    }
}
