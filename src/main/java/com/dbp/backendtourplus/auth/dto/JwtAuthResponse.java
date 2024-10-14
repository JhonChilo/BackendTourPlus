package com.dbp.backendtourplus.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthResponse {
    private String token;
    private String type = "Bearer";
}
