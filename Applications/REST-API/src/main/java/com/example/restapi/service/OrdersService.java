package com.example.restapi.service;

import com.example.restapi.entity.Orders;
import com.example.restapi.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;


    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    public Orders addOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Optional<Orders> readOneOrder(UUID id) {
        return ordersRepository.findById(id);
    }

    public List<Orders> readOrders() {
        return ordersRepository.findAll();
    }

    public boolean updateOrders(Orders orders) {
        boolean test = false;
        try {
            ordersRepository.save(orders);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteOrders(UUID id) {
        boolean test = false;
        try {
            ordersRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

    public List<Orders> ReadOrdersByUserId(UUID id){
        return ordersRepository.findAllByUsers_UsersId(id);
    }
}
