package com.dbp.backendtourplus.tourinstance.dto;

import com.dbp.backendtourplus.tourinstance.domain.LanguageStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TourInstanceDto {
    private Long id;

    @NotNull(message = "El idioma no puede ser nulo")
    private LanguageStatus languageStatus;

    @Positive(message = "La duración del tour debe ser un número positivo")
    private Double tourDuration;

    private Long tourId;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDateTime startDate;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDateTime endDate;
}
