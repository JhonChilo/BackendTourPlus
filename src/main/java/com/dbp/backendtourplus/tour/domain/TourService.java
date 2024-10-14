package com.dbp.backendtourplus.tour.domain;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.tour.dto.TourDto;
import com.dbp.backendtourplus.tour.infrastructure.TourRepository;
import com.dbp.backendtourplus.tourcategory.domain.TourCategory;
import com.dbp.backendtourplus.tourcategory.infrastructure.TourCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final TourCategoryRepository tourCategoryRepository;

    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    public Tour findById(Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found with id: " + id));
    }

    public Tour save(TourDto tourDto) {
        Tour tour = new Tour();

        populateTourFromDto(tour, tourDto);

        TourCategory category = tourCategoryRepository.findById(tourDto.getTourCategory())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + tourDto.getTourCategory()));
        tour.setTourCategory(category);

        return tourRepository.save(tour);
    }

    public Tour updateTour(Long id, TourDto tourDto) {
        Tour existingTour = findById(id);
        populateTourFromDto(existingTour, tourDto);
        return tourRepository.save(existingTour);
    }

    public void deleteById(Long id) {
        if (!tourRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tour not found with id: " + id);
        }
        tourRepository.deleteById(id);
    }

    private void populateTourFromDto(Tour tour, TourDto tourDto) {
        tour.setTitle(tourDto.getTitle());
        tour.setDescription(tourDto.getDescription());
        tour.setPrice(tourDto.getPrice());
        TourCategory category = getTourCategoryById(tourDto.getTourCategory());
        tour.setTourCategory(category);
        tour.setImageUrls(tourDto.getImageUrls());
    }

    private TourCategory getTourCategoryById(Long categoryId) {
        return tourCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("TourCategory not found with id: " + categoryId));
    }
}
