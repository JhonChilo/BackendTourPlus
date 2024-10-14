package com.dbp.backendtourplus.tour.domain;

import com.dbp.backendtourplus.company.domain.Company;
import com.dbp.backendtourplus.location.domain.Location;
import com.dbp.backendtourplus.tourcategory.domain.TourCategory;
import com.dbp.backendtourplus.tourcategory.domain.TourCategoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TourTest {

    private Tour tour;

    @BeforeEach
    void setUp() {
        TourCategory tourCategory = new TourCategory();
        tourCategory.setName(TourCategoryStatus.NATURE);
        tourCategory.setDescription("Explore the wonders of nature.");

        Location location = new Location();
        location.setAddress("123 Forest Lane");
        location.setLatitude(35.6895);
        location.setLongitude(139.6917);

        Company company = new Company();
        company.setName("Nature Tours");
        company.setRuc("1234567890");

        tour = new Tour();
        tour.setTitle("Forest Adventure");
        tour.setDescription("A thrilling journey through the forest.");
        tour.setPrice(100.0);
        tour.setTourCategory(tourCategory);
        tour.setLocation(location);
        tour.setCompany(company);
        tour.setImageUrls(Arrays.asList("url1.jpg", "url2.jpg"));
    }

    @Test
    void testTourTitle() {
        assertEquals("Forest Adventure", tour.getTitle(), "El título del tour debería ser 'Forest Adventure'");
    }

    @Test
    void testTourDescription() {
        assertEquals("A thrilling journey through the forest.", tour.getDescription(), "La descripción debería ser correcta");
    }

    @Test
    void testTourPrice() {
        assertEquals(100.0, tour.getPrice(), "El precio debería ser 100.0");
    }

    @Test
    void testTourCategoryAssociation() {
        assertEquals(TourCategoryStatus.NATURE, tour.getTourCategory().getName(), "El nombre de la categoría debería ser 'NATURE'");
    }

    @Test
    void testLocationAssociation() {
        assertEquals("123 Forest Lane", tour.getLocation().getAddress(), "La dirección de la ubicación debería ser '123 Forest Lane'");
        assertEquals(35.6895, tour.getLocation().getLatitude(), "La latitud debería ser 35.6895");
        assertEquals(139.6917, tour.getLocation().getLongitude(), "La longitud debería ser 139.6917");
    }

    @Test
    void testCompanyAssociation() {
        assertEquals("Nature Tours", tour.getCompany().getName(), "El nombre de la empresa debería ser 'Nature Tours'");
        assertEquals("1234567890", tour.getCompany().getRuc(), "El RUC de la empresa debería ser '1234567890'");
    }

    @Test
    void testImageUrls() {
        assertEquals(2, tour.getImageUrls().size(), "Debería haber 2 URLs de imágenes");
        assertTrue(tour.getImageUrls().contains("url1.jpg"), "La URL 'url1.jpg' debería estar presente");
    }
}
