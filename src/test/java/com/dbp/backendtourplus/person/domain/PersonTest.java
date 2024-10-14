package com.dbp.backendtourplus.person.domain;

import com.dbp.backendtourplus.booking.domain.Booking;
import com.dbp.backendtourplus.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonTest {

    private Person person;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");

        person = new Person();
        person.setFirstname("John");
        person.setLastname("Doe");
        person.setUser(user);
        person.setBookings(new ArrayList<>());
    }

    @Test
    void testFirstName() {
        assertEquals("John", person.getFirstname(), "El nombre debería ser John");
    }

    @Test
    void testLastName() {
        assertEquals("Doe", person.getLastname(), "El apellido debería ser Doe");
    }

    @Test
    void testUserAssociation() {
        assertEquals("test@example.com", person.getUser().getEmail(), "El usuario debería tener el correo 'test@example.com'");
    }

    @Test
    void testBookingsList() {
        assertNotNull(person.getBookings(), "La lista de reservas no debería ser nula");
        assertEquals(0, person.getBookings().size(), "La lista de reservas debería estar vacía inicialmente");

        Booking booking = new Booking();
        person.getBookings().add(booking);

        assertEquals(1, person.getBookings().size(), "La lista de reservas debería contener 1 elemento después de agregar una reserva");
    }

    @Test
    void testSetPersonProperties() {
        person.setFirstname("Jane");
        person.setLastname("Smith");

        assertEquals("Jane", person.getFirstname(), "El nombre debería ser Jane");
        assertEquals("Smith", person.getLastname(), "El apellido debería ser Smith");
    }
}
