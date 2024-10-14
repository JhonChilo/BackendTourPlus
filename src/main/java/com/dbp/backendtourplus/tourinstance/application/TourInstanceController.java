package com.dbp.backendtourplus.tourinstance.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.tourinstance.domain.TourInstance;
import com.dbp.backendtourplus.tourinstance.dto.TourInstanceDto;
import com.dbp.backendtourplus.tourinstance.domain.TourInstanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-instances")
@RequiredArgsConstructor
public class TourInstanceController {

    private final TourInstanceService tourInstanceService;

    @GetMapping
    public List<TourInstance> getAllTourInstances() {
        return tourInstanceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourInstance> getTourInstanceById(@PathVariable Long id) {
        TourInstance tourInstance = tourInstanceService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TourInstance not found with id: " + id));
        return ResponseEntity.ok(tourInstance);
    }

    @PostMapping
    public ResponseEntity<TourInstance> createTourInstance(@Valid @RequestBody TourInstanceDto tourInstanceDto) {
        TourInstance createdTourInstance = tourInstanceService.save(tourInstanceDto);
        return ResponseEntity.ok(createdTourInstance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourInstance> updateTourInstance(@PathVariable Long id, @RequestBody TourInstanceDto tourInstanceDto) {
        TourInstance updatedTourInstance = tourInstanceService.update(id, tourInstanceDto);
        return ResponseEntity.ok(updatedTourInstance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourInstance(@PathVariable Long id) {
        tourInstanceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
