package com.dbp.backendtourplus.location.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.location.domain.Location;
import com.dbp.backendtourplus.location.domain.LocationService;
import com.dbp.backendtourplus.location.dto.LocationDto;
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
public class LocationControllerTest {

    private LocationController locationController;
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationService = Mockito.mock(LocationService.class);
        locationController = new LocationController(locationService);
    }

    @Test
    void testGetAllLocations() {
        Location location1 = new Location();
        Location location2 = new Location();
        when(locationService.getAllLocations()).thenReturn(Arrays.asList(location1, location2));

        List<Location> result = locationController.getAllLocations();

        assertEquals(2, result.size());
        verify(locationService, times(1)).getAllLocations();
    }

    @Test
    void testGetLocationById_Found() {
        Long locationId = 1L;
        Location location = new Location();
        when(locationService.getLocationById(locationId)).thenReturn(Optional.of(location));

        ResponseEntity<Location> response = locationController.getLocationById(locationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(location, response.getBody());
    }

    @Test
    void testGetLocationById_NotFound() {
        Long locationId = 1L;
        when(locationService.getLocationById(locationId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> locationController.getLocationById(locationId));
    }

    @Test
    void testCreateLocation() {
        LocationDto locationDto = new LocationDto();
        Location location = new Location();
        when(locationService.createLocation(locationDto)).thenReturn(location);

        Location result = locationController.createLocation(locationDto);

        assertEquals(location, result);
        verify(locationService, times(1)).createLocation(locationDto);
    }

    @Test
    void testUpdateLocation() {
        Long locationId = 1L;
        LocationDto locationDto = new LocationDto();
        Location updatedLocation = new Location();
        when(locationService.updateLocation(locationId, locationDto)).thenReturn(updatedLocation);

        ResponseEntity<Location> response = locationController.updateLocation(locationId, locationDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedLocation, response.getBody());
        verify(locationService, times(1)).updateLocation(locationId, locationDto);
    }

    @Test
    void testDeleteLocation() {
        Long locationId = 1L;
        doNothing().when(locationService).deleteLocation(locationId);

        ResponseEntity<Void> response = locationController.deleteLocation(locationId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(locationService, times(1)).deleteLocation(locationId);
    }
}
