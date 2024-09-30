package com.dbp.backendtourplus.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String firstName;
    @NotBlank(message = "Name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    private String email;


    @NotNull(message = "Trips cannot be null")
    @Positive(message = "Trips must be greater than 0")
    private Integer phoneNumber;


    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    private List<String> reviews;
}
