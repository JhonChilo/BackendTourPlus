package com.dbp.backendtourplus.tour.application;

import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.tour.dto.TourRequestDto;
import com.dbp.backendtourplus.tour.dto.TourResponseDto;
import com.dbp.backendtourplus.tour.domain.TourService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;
    @Autowired
    private ModelMapper modelMapper;

    TourController(TourService tourService) {
        this.modelMapper = new ModelMapper();
        this.tourService = tourService;
    }


    @PostMapping
    public ResponseEntity<Tour> createTour(@Valid @PathVariable TourRequestDto tourRequestDto) {
        Tour newTour = modelMapper.map(tourRequestDto, Tour.class);
        Tour savedTour = tourService.save(newTour);
        URI location = URI.create("/tour/" + savedTour.getId());
        return ResponseEntity.created(location).body(savedTour);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour) {
        return ResponseEntity.ok(tourService.partialUpdate(id, tour));
    }






}
