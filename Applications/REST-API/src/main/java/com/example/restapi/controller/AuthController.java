package com.example.restapi.controller;

import com.example.restapi.service.UsersService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String login(@RequestBody LoginRequest loginRequest) {
        // Vérifiez les informations d'authentification de l'utilisateur
        boolean authenticated = usersService.verifyUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (authenticated) {
            // Générez un token JWT pour l'utilisateur authentifié
            String token = usersService.generateToken(loginRequest.getEmail(), loginRequest.getPassword());
            // Retournez le token dans la réponse
            return token;
        } else {
            // Gérer l'échec de l'authentification, par exemple, retourner une erreur
            return "Authentication failed";
        }
    }
}

@Data
class LoginRequest {
    private String email;
    private String password;

    // getters and setters
}
