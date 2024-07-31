package com.example.restapi.service;

import com.example.restapi.entity.Sessions;
import com.example.restapi.dto.SessionsDTO;
import com.example.restapi.entity.Users;
import com.example.restapi.mapper.SessionsMapper;
import com.example.restapi.repository.SessionsRepository;
import com.example.restapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SessionsService {

    private final SessionsRepository sessionsRepository;
    private final SessionsMapper sessionsMapper;

    @Autowired
    public SessionsService(SessionsRepository sessionsRepository, SessionsMapper sessionsMapper) {
        this.sessionsRepository = sessionsRepository;
        this.sessionsMapper = sessionsMapper;
    }

    public SessionsDTO addSessions(SessionsDTO sessionsDTO) {
        Sessions sessions = sessionsMapper.toSessions(sessionsDTO);
        return sessionsMapper.toSessionsDto(sessionsRepository.save(sessions));
    }

    public Optional<SessionsDTO> readOneSessions(UUID id) {
        return sessionsRepository.findById(id).map(sessionsMapper::toSessionsDto);
    }

    public List<SessionsDTO> readSessions() {
        return sessionsRepository.findAll().stream()
                .map(sessionsMapper::toSessionsDto)
                .collect(Collectors.toList());
    }

    public boolean updateSessions(SessionsDTO sessionsDTO) {
        boolean test = false;
        try {
            Sessions sessions = sessionsMapper.toSessions(sessionsDTO);
            sessionsRepository.save(sessions);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteSessions(UUID id) {
        boolean test = false;
        try {
            sessionsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public SessionsDTO readOneSessionsByUsersId(UUID usersId){
        return sessionsMapper.toSessionsDto(sessionsRepository.findByUsers_UsersId(usersId));
    }
}
