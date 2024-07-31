package com.example.restapi.controller;

import com.example.restapi.dto.OrdersDTO;
import com.example.restapi.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrdersRestController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("orders")
    public OrdersDTO createOrders(@RequestBody OrdersDTO ordersDTO) {
        return ordersService.addOrders(ordersDTO);
    }

    @GetMapping("orders")
    public List<OrdersDTO> getAllOrders() {
        return ordersService.readOrders();
    }

    @GetMapping("orders/{id}")
    public Optional<OrdersDTO> getOneOrders(@PathVariable("id") UUID id) {
        return ordersService.readOneOrders(id);
    }

    @PutMapping("orders/{id}")
    public boolean UpdateOrders(@RequestBody OrdersDTO ordersDTO) {
        boolean resultat = false;
        try {
            resultat = ordersService.updateOrders(ordersDTO);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("orders/{id}")
    public boolean DeleteOrders(@PathVariable("id") UUID id) {
        return ordersService.deleteOrders(id);
    }
}
