package com.example.restapi.service;

import com.example.restapi.dto.CartItemsDTO;
import com.example.restapi.entity.Orders;
import com.example.restapi.dto.OrdersDTO;
import com.example.restapi.mapper.OrdersMapper;
import com.example.restapi.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, OrdersMapper ordersMapper) {
        this.ordersRepository = ordersRepository;
        this.ordersMapper = ordersMapper;
    }

    public OrdersDTO addOrders(OrdersDTO ordersDTO) {
        Orders orders = ordersMapper.toOrders(ordersDTO);
        return ordersMapper.toOrdersDto(ordersRepository.save(orders));
    }

    public Optional<OrdersDTO> readOneOrders(UUID id) {
        return ordersRepository.findById(id).map(ordersMapper::toOrdersDto);
    }

    public List<OrdersDTO> readOrders() {
        return ordersRepository.findAll().stream()
                .map(ordersMapper::toOrdersDto)
                .collect(Collectors.toList());
    }

    public boolean updateOrders(OrdersDTO ordersDTO) {
        boolean test = false;
        try {
            Orders orders = ordersMapper.toOrders(ordersDTO);
            ordersRepository.save(orders);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteOrders(UUID id) {
        boolean test = false;
        try {
            ordersRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public List<OrdersDTO> ReadOrdersByUserId(UUID id) {
        return ordersRepository.findAllByUsers_UsersId(id).stream()
                .map(ordersMapper::toOrdersDto)
                .collect(Collectors.toList());
    }
}
