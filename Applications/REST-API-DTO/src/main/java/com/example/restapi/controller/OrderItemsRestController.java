package com.example.restapi.controller;

import com.example.restapi.dto.OrderItemsDTO;
import com.example.restapi.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderItemsRestController {

    @Autowired
    private OrderItemsService orderItemsService;

    @PostMapping("orderItems")
    public OrderItemsDTO createOrderItems(@RequestBody OrderItemsDTO orderItemsDTO) {
        return orderItemsService.addOrderItems(orderItemsDTO);
    }

    @GetMapping("orderItems")
    public List<OrderItemsDTO> getAllOrderItems() {
        return orderItemsService.readOrderItems();
    }

    @GetMapping("orderItems/{id}")
    public Optional<OrderItemsDTO> getOneOrderItems(@PathVariable("id") UUID id) {
        return orderItemsService.readOneOrderItems(id);
    }

    @PutMapping("orderItems/{id}")
    public boolean UpdateOrderItems(@RequestBody OrderItemsDTO orderItemsDTO) {
        boolean resultat = false;
        try {
            resultat = orderItemsService.updateOrderItems(orderItemsDTO);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("orderItems/{id}")
    public boolean DeleteOrderItems(@PathVariable("id") UUID id) {
        return orderItemsService.deleteOrderItems(id);
    }
}
