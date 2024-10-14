package com.dbp.backendtourplus.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDto {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El RUC no puede estar vacío")
    private String ruc;
}