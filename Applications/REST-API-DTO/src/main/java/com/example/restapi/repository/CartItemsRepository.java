package com.example.restapi.repository;

import com.example.restapi.entity.CartItems;
import com.example.restapi.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemsRepository extends JpaRepository<CartItems, UUID> {
    public List<CartItems> findAllByCarts_CartsId(UUID id);
}
