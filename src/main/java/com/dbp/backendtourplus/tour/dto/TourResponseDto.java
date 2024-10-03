package com.dbp.backendtourplus.tour.dto;

import com.dbp.backendtourplus.review.domain.Review;
import com.dbp.backendtourplus.tour.domain.TourCategory;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class TourResponseDto {

    private String tittle;

    private String description;

    private String iterary;

    private Double price;

    private TourCategory tourCategory;

    @OneToMany
    private List<Review> reviews;
}
