package com.example.restapi.repository;

import com.example.restapi.entity.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionsRepository extends JpaRepository<Sessions, UUID> {
    public Sessions findByUsers_UsersId(UUID usersId);
}
