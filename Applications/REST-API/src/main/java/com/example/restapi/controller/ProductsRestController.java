package com.example.restapi.controller;

import com.example.restapi.entity.Products;
import com.example.restapi.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class ProductsRestController {

private final ProductsService productsService;

public ProductsRestController(ProductsService productsService){
        this.productsService = productsService;
        }
@PostMapping("products")
public Products createProducts(@RequestBody Products products) {
        products.setProductsId(UUID.randomUUID());
        productsService.addProducts(products);
        return products;
        }

@GetMapping("products")
public List<Products> getAllProducts() {
        return productsService.readProducts();
        }

@GetMapping("product/{id}")
public Optional<Products> getOneProducts(@PathVariable("id") UUID id) {
        return productsService.readOneProduct(id);
        }

@PutMapping("product/{id}")
public boolean UpdateProducts(@PathVariable("id") UUID id, @RequestBody Products products) {
        Optional<Products> product_test = productsService.readOneProduct(id);
        boolean resultat = false;
        if(product_test!=null) {
        resultat = productsService.updateProducts(products);
        }
        return resultat;
        }

@DeleteMapping("product/{id}")
public boolean DeleteProducts(@PathVariable("id") UUID id) {
        return productsService.deleteProducts(id);
        }
        }
