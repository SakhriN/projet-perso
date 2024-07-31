package com.example.restapi.repository;

import com.example.restapi.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewsRepository extends JpaRepository<Reviews, UUID> {
}
