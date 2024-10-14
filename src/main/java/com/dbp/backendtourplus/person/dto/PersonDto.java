package com.dbp.backendtourplus.person.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonDto {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstname;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastname;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String email;
}
