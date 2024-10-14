package com.dbp.backendtourplus.review.dto;

import com.dbp.backendtourplus.user.domain.User;
import com.dbp.backendtourplus.tour.domain.Tour;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReviewDto {
    @NotNull(message = "El autor no puede ser nulo")
    private User author;

    @NotBlank(message = "El comentario no puede estar vacío")
    private String comment;

    @Positive(message = "La puntuación debe ser un número positivo")
    private Integer rating;

    @NotNull(message = "El tour no puede ser nulo")
    private Tour tour;
}
