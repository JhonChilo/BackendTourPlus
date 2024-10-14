package com.dbp.backendtourplus.company.domain;

import com.dbp.backendtourplus.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@SpringBootTest
public class CompanyTest {

    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setName("TourCompany");
        company.setRuc("12345678901");

        User user = new User();
        user.setEmail("test@example.com");
        company.setUser(user);
    }

    @Test
    void testCompanyName() {
        assertEquals("TourCompany", company.getName());
    }

    @Test
    void testCompanyRUC() {
        assertEquals("12345678901", company.getRuc());
    }

    @Test
    void testUserAssignment() {
        assertNotNull(company.getUser());
        assertEquals("test@example.com", company.getUser().getEmail());
    }

    @Test
    void testToursInitialization() {
        company.setTours(new ArrayList<>());
        assertNotNull(company.getTours());
        assertTrue(company.getTours().isEmpty());
    }
}
