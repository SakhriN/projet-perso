package com.example.restapi.controller;

import com.example.restapi.entity.Carts;
import com.example.restapi.service.CartsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class CartsRestController {

    private final CartsService cartsService;

    public CartsRestController(CartsService cartsService) {
        this.cartsService = cartsService;
    }

    @PostMapping("carts")
    public Carts createCarts(@RequestBody Carts carts) {
        carts.setCartsId(UUID.randomUUID());
        cartsService.addCarts(carts);
        return carts;
    }

    @GetMapping("carts")
    public List<Carts> getAllCarts() {
        return cartsService.readCarts();
    }

    @GetMapping("cart/{id}")
    public Optional<Carts> getOneCarts(@PathVariable("id") UUID id) {
        return cartsService.readOneCart(id);
    }

    @PutMapping("cart/{id}")
    public boolean UpdateCarts(@PathVariable("id") UUID id, @RequestBody Carts carts) {
        Optional<Carts> cart_test = cartsService.readOneCart(id);
        boolean resultat = false;
        if (cart_test != null) {
            resultat = cartsService.updateCarts(carts);
        }
        return resultat;
    }

    @DeleteMapping("cart/{id}")
    public boolean DeleteCarts(@PathVariable("id") UUID id) {
        return cartsService.deleteCarts(id);
    }

    @GetMapping("cart/session/{sessionId}")
    public Carts getCartsBySessionsId(@PathVariable("sessionId") UUID sessionId) {
        return cartsService.ReadCartsBySessionId(sessionId);
    }
}
