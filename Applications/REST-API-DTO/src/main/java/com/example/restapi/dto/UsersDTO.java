package com.example.restapi.dto;

import com.example.restapi.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDTO {

    @Id
    private UUID usersId;
    private String usersUsername;
    private String usersEmail;
    private String usersPassword;
    private String usersFirstname;
    private String usersLastname;
    private String usersAddress;
    private String usersPhonenumber;
    private Role usersRole;

}
