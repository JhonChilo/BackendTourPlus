package com.dbp.backendtourplus.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterReq {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstname;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastname;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
