package com.example.restapi.repository;

import com.example.restapi.entity.Carts;
import com.example.restapi.entity.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartsRepository extends JpaRepository<Carts, UUID> {
    public Carts findCartsBySessions_SessionsId(UUID id);
}
