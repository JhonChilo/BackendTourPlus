package com.dbp.backendtourplus.tourcategory.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.tourcategory.domain.TourCategory;
import com.dbp.backendtourplus.tourcategory.domain.TourCategoryService;
import com.dbp.backendtourplus.tourcategory.dto.TourCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-categories")
@RequiredArgsConstructor
public class TourCategoryController {

    private final TourCategoryService tourCategoryService;

    @GetMapping
    public ResponseEntity<List<TourCategory>> getAllTourCategories() {
        List<TourCategory> categories = tourCategoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourCategory> getTourCategoryById(@PathVariable Long id) {
        TourCategory tourCategory = tourCategoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour Category not found with id: " + id));
        return ResponseEntity.ok(tourCategory);
    }

    @PostMapping
    public ResponseEntity<TourCategory> createTourCategory(@RequestBody TourCategoryDto tourCategoryDto) {
        TourCategory createdCategory = tourCategoryService.save(tourCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourCategory> updateTourCategory(@PathVariable Long id, @RequestBody TourCategoryDto tourCategoryDto) {
        TourCategory updatedCategory = tourCategoryService.update(id, tourCategoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourCategory(@PathVariable Long id) {
        tourCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
