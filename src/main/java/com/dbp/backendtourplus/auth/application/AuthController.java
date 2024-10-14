package com.dbp.backendtourplus.auth.application;

import com.dbp.backendtourplus.auth.domain.AuthService;
import com.dbp.backendtourplus.auth.dto.JwtAuthResponse;
import com.dbp.backendtourplus.auth.dto.LoginReq;
import com.dbp.backendtourplus.auth.dto.RegisterReq;
import com.dbp.backendtourplus.auth.exceptions.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq registerReq) {
        try {
            JwtAuthResponse jwtAuthResponse = authService.register(registerReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(jwtAuthResponse);
        } catch (UserAlreadyExistException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        try {
            JwtAuthResponse jwtAuthResponse = authService.login(loginReq);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        }
    }
}