package com.example.restapi.controller;

import com.example.restapi.dto.SessionsDTO;
import com.example.restapi.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class SessionsRestController {

    @Autowired
    private SessionsService sessionsService;


    @PostMapping("sessions")
    public SessionsDTO createSessions() {
        SessionsDTO sessionsDTO = new SessionsDTO();
        sessionsDTO.setSessionsCreationDate(Date.valueOf(LocalDate.now()));
        sessionsDTO.setSessionsExpireDate(Date.valueOf(LocalDate.now().plusDays(1)));
        sessionsDTO.setUsersId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        return sessionsService.addSessions(sessionsDTO);
    }

    @GetMapping("sessions")
    public List<SessionsDTO> getAllSessions() {
        return sessionsService.readSessions();
    }

    @GetMapping("sessions/{id}")
    public Optional<SessionsDTO> getOneSessions(@PathVariable("id") UUID id) {
        return sessionsService.readOneSessions(id);
    }

    @PutMapping("sessions/{id}")
    public boolean UpdateSessions(@RequestBody SessionsDTO sessionsDTO) {
        boolean resultat = false;
        try {
            resultat = sessionsService.updateSessions(sessionsDTO);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("sessions/{id}")
    public boolean DeleteSessions(@PathVariable("id") UUID id) {
        return sessionsService.deleteSessions(id);
    }

    @GetMapping("sessions/users/{usersId}")
    public SessionsDTO getSessionsByUserId(@PathVariable UUID usersId) {
        return sessionsService.readOneSessionsByUsersId(usersId);
    }


}
