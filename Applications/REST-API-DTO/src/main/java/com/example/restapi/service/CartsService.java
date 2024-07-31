package com.example.restapi.service;

import com.example.restapi.entity.Carts;
import com.example.restapi.dto.CartsDTO;
import com.example.restapi.mapper.CartsMapper;
import com.example.restapi.repository.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartsService {

    private final CartsRepository cartsRepository;
    private final CartsMapper cartsMapper;

    @Autowired
    public CartsService(CartsRepository cartsRepository, CartsMapper cartsMapper) {
        this.cartsRepository = cartsRepository;
        this.cartsMapper = cartsMapper;
    }

    public CartsDTO addCarts(CartsDTO cartsDTO) {
        Carts carts = cartsMapper.toCarts(cartsDTO);
        return cartsMapper.toCartsDto(cartsRepository.save(carts));
    }

    public Optional<CartsDTO> readOneCarts(UUID id) {
        return cartsRepository.findById(id).map(cartsMapper::toCartsDto);
    }

    public List<CartsDTO> readCarts() {
        return cartsRepository.findAll().stream()
                .map(cartsMapper::toCartsDto)
                .collect(Collectors.toList());
    }

    public boolean updateCarts(CartsDTO cartsDTO) {
        boolean test = false;
        try {
            Carts carts = cartsMapper.toCarts(cartsDTO);
            cartsRepository.save(carts);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteCarts(UUID id) {
        boolean test = false;
        try {
            cartsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public CartsDTO ReadCartsBySessionId(UUID sessionId) {
        Carts carts = cartsRepository.findCartsBySessions_SessionsId(sessionId);
        return cartsMapper.toCartsDto(carts);
    }

}
