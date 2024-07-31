package com.example.restapi.repository;

import com.example.restapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    public List<Orders> findAllByUsers_UsersId(UUID id);
}
