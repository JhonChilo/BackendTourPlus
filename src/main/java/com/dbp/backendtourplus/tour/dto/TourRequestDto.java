package com.dbp.backendtourplus.tour.dto;

import com.dbp.backendtourplus.tour.domain.TourCategory;
import lombok.Data;

@Data
public class TourRequestDto {

    private String title;

    private String description;

    private String iterary;

    private Double price;

    private TourCategory tourCategory;


}
