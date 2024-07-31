package com.example.restapi.service;

import com.example.restapi.entity.Products;
import com.example.restapi.dto.ProductsDTO;
import com.example.restapi.mapper.ProductsMapper;
import com.example.restapi.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final ProductsMapper productsMapper;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, ProductsMapper productsMapper) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
    }

    public ProductsDTO addProducts(ProductsDTO productsDTO) {
        Products products = productsMapper.toProducts(productsDTO);
        return productsMapper.toProductsDto(productsRepository.save(products));
    }

    public Optional<ProductsDTO> readOneProducts(UUID id) {
        return productsRepository.findById(id).map(productsMapper::toProductsDto);
    }

    public List<ProductsDTO> readProducts() {
        return productsRepository.findAll().stream()
                .map(productsMapper::toProductsDto)
                .collect(Collectors.toList());
    }

    public boolean updateProducts(ProductsDTO productsDTO) {
        boolean test = false;
        try {
            Products products = productsMapper.toProducts(productsDTO);
            productsRepository.save(products);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteProducts(UUID id) {
        boolean test = false;
        try {
            productsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

}
