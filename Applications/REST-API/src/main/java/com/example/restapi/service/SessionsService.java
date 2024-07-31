package com.example.restapi.service;

import com.example.restapi.entity.Sessions;
import com.example.restapi.repository.SessionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionsService {

    private final SessionsRepository sessionsRepository;


    public SessionsService(SessionsRepository sessionsRepository) {
        this.sessionsRepository = sessionsRepository;
    }


    public Sessions addSessions(Sessions sessions) {
        return sessionsRepository.save(sessions);
    }

    public Optional<Sessions> readOneSession(UUID id) {
        return sessionsRepository.findById(id);
    }

    public List<Sessions> readSessions() {
        return sessionsRepository.findAll();
    }

    public boolean updateSessions(Sessions sessions) {
        boolean test = false;
        try {
            sessionsRepository.save(sessions);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteSessions(UUID id) {
        boolean test = false;
        try {
            sessionsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

}
