package com.example.restapi.controller;

import com.example.restapi.entity.Sessions;
import com.example.restapi.service.SessionsService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class SessionsRestController {

    private final SessionsService sessionsService;

    public SessionsRestController(SessionsService sessionsService){
        this.sessionsService = sessionsService;
    }
    @PostMapping("sessions")
    public Sessions createSessions() {
        Sessions sessions = new Sessions();
        sessions.setSessionsId(UUID.randomUUID());
        sessions.setSessionsCreationDate(Date.valueOf(LocalDate.now()));
        sessions.setSessionsExpireDate(Date.valueOf(LocalDate.now().plusDays(1)));
        sessionsService.addSessions(sessions);
        return sessions;
    }

    @GetMapping("sessions")
    public List<Sessions> getAllSessions() {
        return sessionsService.readSessions();
    }

    @GetMapping("session/{id}")
    public Optional<Sessions> getOneSessions(@PathVariable("id") UUID id) {
        return sessionsService.readOneSession(id);
    }

    @PutMapping("session/{id}")
    public boolean UpdateSessions(@PathVariable("id") UUID id, @RequestBody Sessions sessions) {
        Optional<Sessions> session_test = sessionsService.readOneSession(id);
        boolean resultat = false;
        if(session_test!=null) {
            resultat = sessionsService.updateSessions(sessions);
        }
        return resultat;
    }

    @DeleteMapping("session/{id}")
    public boolean DeleteSessions(@PathVariable("id") UUID id) {
        return sessionsService.deleteSessions(id);
    }
}
