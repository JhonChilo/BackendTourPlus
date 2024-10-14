package com.dbp.backendtourplus.tour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TourDto {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 300, message = "La descripción no puede exceder los 300 caracteres")
    private String description;

    @Positive(message = "El precio debe ser un número positivo")
    private Double price;

    @NotBlank(message = "La categoría del tour no puede estar vacía")
    private Long tourCategory;

    @NotEmpty(message = "La lista de imágenes no puede estar vacía")
    private List<String> imageUrls;
}

