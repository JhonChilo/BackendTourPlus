package com.dbp.backendtourplus.location.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location();
        location.setLatitude(40.7128);
        location.setLongitude(-74.0060);
        location.setAddress("New York, NY");
    }

    @Test
    void testLatitude() {
        assertEquals(40.7128, location.getLatitude());
    }

    @Test
    void testLongitude() {
        assertEquals(-74.0060, location.getLongitude());
    }

    @Test
    void testAddress() {
        assertEquals("New York, NY", location.getAddress());
    }

    @Test
    void testSetLocationProperties() {
        location.setLatitude(34.0522);
        location.setLongitude(-118.2437);
        location.setAddress("Los Angeles, CA");

        assertEquals(34.0522, location.getLatitude(), "La latitud debería ser 34.0522");
        assertEquals(-118.2437, location.getLongitude(), "La longitud debería ser -118.2437");
        assertEquals("Los Angeles, CA", location.getAddress(), "La dirección debería ser 'Los Angeles, CA'");
    }
}
