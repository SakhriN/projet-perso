package com.example.restapi.service;

import com.example.restapi.dto.CartItemsDTO;
import com.example.restapi.entity.OrderItems;
import com.example.restapi.dto.OrderItemsDTO;
import com.example.restapi.mapper.OrderItemsMapper;
import com.example.restapi.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;
    private final OrderItemsMapper orderItemsMapper;

    @Autowired
    public OrderItemsService(OrderItemsRepository orderItemsRepository, OrderItemsMapper orderItemsMapper) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderItemsMapper = orderItemsMapper;
    }

    public OrderItemsDTO addOrderItems(OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = orderItemsMapper.toOrderItems(orderItemsDTO);
        return orderItemsMapper.toOrderItemsDto(orderItemsRepository.save(orderItems));
    }

    public Optional<OrderItemsDTO> readOneOrderItems(UUID id) {
        return orderItemsRepository.findById(id).map(orderItemsMapper::toOrderItemsDto);
    }

    public List<OrderItemsDTO> readOrderItems() {
        return orderItemsRepository.findAll().stream()
                .map(orderItemsMapper::toOrderItemsDto)
                .collect(Collectors.toList());
    }

    public boolean updateOrderItems(OrderItemsDTO orderItemsDTO) {
        boolean test = false;
        try {
            OrderItems orderItems = orderItemsMapper.toOrderItems(orderItemsDTO);
            orderItemsRepository.save(orderItems);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteOrderItems(UUID id) {
        boolean test = false;
        try {
            orderItemsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public List<OrderItemsDTO> readOrderItemsByOrderId(UUID id) {
        return orderItemsRepository.findAllByOrders_OrdersId(id).stream()
                .map(orderItemsMapper::toOrderItemsDto)
                .collect(Collectors.toList());
    }


}
