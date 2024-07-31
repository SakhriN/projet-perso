package com.example.restapi.repository;

import com.example.restapi.entity.OrderItems;
import com.example.restapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderItemsRepository extends JpaRepository<OrderItems, UUID> {
    public List<OrderItems> findAllByOrders_OrdersId(UUID id);
}
