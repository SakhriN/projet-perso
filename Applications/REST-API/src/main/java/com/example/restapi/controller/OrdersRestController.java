package com.example.restapi.controller;

import com.example.restapi.entity.Orders;
import com.example.restapi.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class OrdersRestController {

private final OrdersService ordersService;

public OrdersRestController(OrdersService ordersService){
        this.ordersService = ordersService;
        }
@PostMapping("orders")
public Orders createOrders(@RequestBody Orders orders) {
        orders.setOrdersId(UUID.randomUUID());
        ordersService.addOrders(orders);
        return orders;
        }

@GetMapping("orders")
public List<Orders> getAllOrders() {
        return ordersService.readOrders();
        }

@GetMapping("order/{id}")
public Optional<Orders> getOneOrders(@PathVariable("id") UUID id) {
        return ordersService.readOneOrder(id);
        }

@PutMapping("order/{id}")
public boolean UpdateOrders(@PathVariable("id") UUID id, @RequestBody Orders orders) {
        Optional<Orders> order_test = ordersService.readOneOrder(id);
        boolean resultat = false;
        if(order_test!=null) {
        resultat = ordersService.updateOrders(orders);
        }
        return resultat;
        }

@DeleteMapping("order/{id}")
public boolean DeleteOrders(@PathVariable("id") UUID id) {
        return ordersService.deleteOrders(id);
        }

        @GetMapping("orders/{userId}")
        public List<Orders> getOrdersByUserId(@PathVariable("userId") UUID userId){
        return ordersService.ReadOrdersByUserId(userId);
        }
        }
