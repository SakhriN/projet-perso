package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID usersId;

    private String usersUsername;

    private String usersEmail;

    private String usersPassword;

    private String usersFirstname;

    private String usersLastname;

    private String usersAddress;

    private String usersPhonenumber;

    private Role usersRole;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Orders> ordersList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Récupérez le nom du rôle de l'utilisateur à partir de l'entité Role
        Role role = this.usersRole;

        // Créez un objet SimpleGrantedAuthority à partir du nom du rôle
        GrantedAuthority authority = new SimpleGrantedAuthority(role.name());

        // Retournez l'autorité dans une liste (ou un ensemble si nécessaire)
        return Collections.singleton(authority); // Utilisation de Set.of() pour créer un ensemble d'une seule autorité
    }

    @Override
    public String getPassword() {
        return usersPassword;
    }

    @Override
    public String getUsername() {
        return usersEmail;
    }




}
