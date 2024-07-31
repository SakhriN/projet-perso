package com.example.restapi.controller;

import com.example.restapi.entity.OrderItems;
import com.example.restapi.service.OrderItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class OrderItemsRestController {

    private final OrderItemsService orderItemsService;

    public OrderItemsRestController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @PostMapping("orderItems")
    public OrderItems createOrderItems(@RequestBody OrderItems orderItems) {
        orderItems.setOrderItemsId(UUID.randomUUID());
        orderItemsService.addOrderItems(orderItems);
        return orderItems;
    }

    @GetMapping("orderItems")
    public List<OrderItems> getAllOrderItems() {
        return orderItemsService.readOrderItems();
    }

    @GetMapping("orderItem/{id}")
    public Optional<OrderItems> getOneOrderItems(@PathVariable("id") UUID id) {
        return orderItemsService.readOneOrderItem(id);
    }

    @PutMapping("orderItem/{id}")
    public boolean updateOrderItems(@PathVariable("id") UUID id, @RequestBody OrderItems orderItems) {
        Optional<OrderItems> orderItem_test = orderItemsService.readOneOrderItem(id);
        boolean resultat = false;
        if (orderItem_test != null) {
            resultat = orderItemsService.updateOrderItems(orderItems);
        }
        return resultat;
    }

    @DeleteMapping("orderItem/{id}")
    public boolean deleteOrderItems(@PathVariable("id") UUID id) {
        return orderItemsService.deleteOrderItems(id);
    }

@GetMapping("orderItems/{orderId}")
    public List<OrderItems> getOrderItemsByOrderId(@PathVariable("orderId") UUID orderId){
        return orderItemsService.readOrderItemsByOrderId(orderId);
}

}
