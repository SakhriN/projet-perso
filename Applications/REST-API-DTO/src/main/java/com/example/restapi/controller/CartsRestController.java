package com.example.restapi.controller;

import com.example.restapi.dto.CartsDTO;
import com.example.restapi.entity.Carts;
import com.example.restapi.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CartsRestController {

    @Autowired
    private CartsService cartsService;

    @PostMapping("carts")
    public CartsDTO createCarts(@RequestBody CartsDTO cartsDTO) {
        return cartsService.addCarts(cartsDTO);
    }

    @GetMapping("carts")
    public List<CartsDTO> getAllCarts() {
        return cartsService.readCarts();
    }

    @GetMapping("carts/{id}")
    public Optional<CartsDTO> getOneCarts(@PathVariable("id") UUID id) {
        return cartsService.readOneCarts(id);
    }

    @PutMapping("carts/{id}")
    public boolean UpdateCarts(@RequestBody CartsDTO cartsDTO) {
        boolean resultat = false;
        try{
            resultat = cartsService.updateCarts(cartsDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("carts/{id}")
    public boolean DeleteCarts(@PathVariable("id") UUID id) {
        return cartsService.deleteCarts(id);
    }

    @GetMapping("carts/sessions/{sessionId}")
    public CartsDTO getCartsBySessionsId(@PathVariable("sessionId") UUID sessionId) {
        return cartsService.ReadCartsBySessionId(sessionId);
    }
}
