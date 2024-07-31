package com.example.restapi.controller;

import com.example.restapi.dto.CartItemsDTO;
import com.example.restapi.dto.CartsDTO;
import com.example.restapi.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CartItemsRestController {

    @Autowired
    private CartItemsService cartItemsService;

    @PostMapping("cartItems")
    public CartItemsDTO createCartItems(@RequestBody CartItemsDTO cartItemsDTO) {
        return cartItemsService.addCartItems(cartItemsDTO);
    }

    @GetMapping("cartItems")
    public List<CartItemsDTO> getAllCartItems() {
        return cartItemsService.readCartItems();
    }

    @GetMapping("cartItems/{id}")
    public Optional<CartItemsDTO> getOneCartItems(@PathVariable("id") UUID id) {
        return cartItemsService.readOneCartItems(id);
    }

    @PutMapping("cartItems/{id}")
    public boolean UpdateCartItems(@RequestBody CartItemsDTO cartItemsDTO) {
        boolean resultat = false;
        try{
            resultat = cartItemsService.updateCartItems(cartItemsDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("cartItems/{id}")
    public boolean DeleteCartItems(@PathVariable("id") UUID id) {
        return cartItemsService.deleteCartItems(id);
    }

    @GetMapping("cartItems/carts/{cartsId}")
    public List<CartItemsDTO> getCartItemsByCartssId(@PathVariable("cartsId") UUID cartsId) {
        return cartItemsService.ReadCartItemsByCartsId(cartsId);
    }
}
