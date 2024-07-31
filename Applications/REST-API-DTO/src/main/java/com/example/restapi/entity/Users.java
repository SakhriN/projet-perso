package com.example.restapi.entity;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "users")
    private List<Orders> ordersList;

    @OneToOne(mappedBy = "users")
    private Sessions sessions;





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

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", usersUsername='" + usersUsername + '\'' +
                ", usersEmail='" + usersEmail + '\'' +
                ", usersPassword='" + usersPassword + '\'' +
                ", usersFirstname='" + usersFirstname + '\'' +
                ", usersLastname='" + usersLastname + '\'' +
                ", usersAddress='" + usersAddress + '\'' +
                ", usersPhonenumber='" + usersPhonenumber + '\'' +
                ", usersRole=" + usersRole +
                '}';
    }
}
