package com.dbp.backendtourplus.tour.application;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.tour.domain.TourService;
import com.dbp.backendtourplus.tour.dto.TourDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tours = tourService.findAll();
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
        Tour tour = tourService.findById(id);
        return ResponseEntity.ok(tour);
    }

    @PostMapping
    public ResponseEntity<Tour> createTour(@Valid @RequestBody TourDto tourDto) {
        Tour createdTour = tourService.save(tourDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTour);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @Valid @RequestBody TourDto tourDto) {
        Tour updatedTour = tourService.updateTour(id, tourDto);
        return ResponseEntity.ok(updatedTour);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
