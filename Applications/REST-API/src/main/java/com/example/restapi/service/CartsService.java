package com.example.restapi.service;

import com.example.restapi.entity.Carts;
import com.example.restapi.entity.Orders;
import com.example.restapi.repository.CartsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartsService {

    private final CartsRepository cartsRepository;


    public CartsService(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }


    public Carts addCarts(Carts carts) {
        return cartsRepository.save(carts);
    }

    public Optional<Carts> readOneCart(UUID id) {
        return cartsRepository.findById(id);
    }

    public List<Carts> readCarts() {
        return cartsRepository.findAll();
    }

    public boolean updateCarts(Carts carts) {
        boolean test = false;
        try {
            cartsRepository.save(carts);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteCarts(UUID id) {
        boolean test = false;
        try {
            cartsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

    public Carts ReadCartsBySessionId(UUID id){
        return cartsRepository.findCartsBySessions_SessionsId(id);
    }

}
