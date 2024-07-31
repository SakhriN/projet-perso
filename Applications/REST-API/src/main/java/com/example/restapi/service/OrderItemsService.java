package com.example.restapi.service;

import com.example.restapi.entity.OrderItems;
import com.example.restapi.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;


    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }


    public OrderItems addOrderItems(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }

    public Optional<OrderItems> readOneOrderItem(UUID id) {
        return orderItemsRepository.findById(id);
    }

    public List<OrderItems> readOrderItems() {
        return orderItemsRepository.findAll();
    }

    public boolean updateOrderItems(OrderItems orderItems) {
        boolean test = false;
        try {
            orderItemsRepository.save(orderItems);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteOrderItems(UUID id) {
        boolean test = false;
        try {
            orderItemsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

    public List<OrderItems> readOrderItemsByOrderId(UUID id){
        return orderItemsRepository.findAllByOrders_OrdersId(id);
    }

}
