package com.dbp.backendtourplus.tour.domain;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.tour.domain.Tour;
import com.dbp.backendtourplus.tour.infrastructure.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    @Autowired
    public  TourRepository tourRepository;

    TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    public Tour partialUpdate (Long ID, Tour tour){
        Tour tourToUpdate = tourRepository.findById(ID).
                orElseThrow(() -> new ResourceNotFoundException("Tour no encontrado"));

        if(tour.getTitle() != null){
            tourToUpdate.setTitle(tour.getTitle());
        }
        if(tour.getDescription() != null){
            tourToUpdate.setDescription(tour.getDescription());
        }
        if(tour.getPrice() != null){
            tourToUpdate.setPrice(tour.getPrice());
        }
        if(tour.getIterary() != null){
            tourToUpdate.setIterary(tour.getIterary());
        }
        if(tour.getTourCategory() != null){
            tourToUpdate.setTourCategory(tour.getTourCategory());
        }
        return tourRepository.save(tourToUpdate);
    }
}
