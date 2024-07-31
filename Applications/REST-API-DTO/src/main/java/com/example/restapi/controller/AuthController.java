package com.example.restapi.controller;

import com.example.restapi.service.UsersService;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<String> login( @Valid @RequestBody LoginRequest loginRequest) {
        boolean authenticated = usersService.verifyUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (authenticated) {
            String token = usersService.generateToken(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }



@Data

static class LoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
}