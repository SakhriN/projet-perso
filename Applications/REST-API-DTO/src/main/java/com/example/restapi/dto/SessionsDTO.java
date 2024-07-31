package com.example.restapi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sessionsId;
    private Date sessionsCreationDate;
    private Date sessionsExpireDate;
    private UUID usersId;
}
