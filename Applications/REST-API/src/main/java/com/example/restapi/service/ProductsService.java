package com.example.restapi.service;

import com.example.restapi.entity.Products;
import com.example.restapi.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;


    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public Products addProducts(Products products) {
        return productsRepository.save(products);
    }

    public Optional<Products> readOneProduct(UUID id) {
        return productsRepository.findById(id);
    }

    public List<Products> readProducts() {
        return productsRepository.findAll();
    }

    public boolean updateProducts(Products products) {
        boolean test = false;
        try {
            productsRepository.save(products);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteProducts(UUID id) {
        boolean test = false;
        try {
            productsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

}
