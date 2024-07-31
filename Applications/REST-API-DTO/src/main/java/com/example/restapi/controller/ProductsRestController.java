package com.example.restapi.controller;

import com.example.restapi.dto.ProductsDTO;
import com.example.restapi.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductsRestController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("products")
    public ProductsDTO createProducts(@RequestBody ProductsDTO productsDTO) {
        return productsService.addProducts(productsDTO);
    }

    @GetMapping("products")
    public List<ProductsDTO> getAllProducts() {
        return productsService.readProducts();
    }

    @GetMapping("products/{id}")
    public Optional<ProductsDTO> getOneProducts(@PathVariable("id") UUID id) {
        return productsService.readOneProducts(id);
    }

    @PutMapping("products/{id}")
    public boolean UpdateProducts(@RequestBody ProductsDTO productsDTO) {
        boolean resultat = false;
        try{
            resultat = productsService.updateProducts(productsDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("products/{id}")
    public boolean DeleteProducts(@PathVariable("id") UUID id) {
        return productsService.deleteProducts(id);
    }
}
