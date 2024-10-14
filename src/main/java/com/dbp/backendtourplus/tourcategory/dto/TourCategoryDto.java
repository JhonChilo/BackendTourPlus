package com.dbp.backendtourplus.tourcategory.dto;

import com.dbp.backendtourplus.tourcategory.domain.TourCategoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TourCategoryDto {
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    private TourCategoryStatus name;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;
}
