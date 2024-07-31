package com.example.restapi.controller;

import com.example.restapi.entity.CartItems;
import com.example.restapi.entity.OrderItems;
import com.example.restapi.service.CartItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class CartItemsRestController {

    private final CartItemsService cartItemsService;

    public CartItemsRestController(CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    @PostMapping("cartItems")
    public CartItems createCartItems(@RequestBody CartItems cartItems) {
        cartItems.setCartItemsId(UUID.randomUUID());
        cartItemsService.addCartItems(cartItems);
        return cartItems;
    }

    @GetMapping("cartItems")
    public List<CartItems> getAllCartItems() {
        return cartItemsService.readCartItems();
    }

    @GetMapping("cartItem/{id}")
    public Optional<CartItems> getOneCartItems(@PathVariable("id") UUID id) {
        return cartItemsService.readOneCartItem(id);
    }

    @PutMapping("cartItem/{id}")
    public boolean UpdateCartItems(@PathVariable("id") UUID id, @RequestBody CartItems cartItems) {
        Optional<CartItems> cartItem_test = cartItemsService.readOneCartItem(id);
        boolean resultat = false;
        if (cartItem_test != null) {
            resultat = cartItemsService.updateCartItems(cartItems);
        }
        return resultat;
    }

    @DeleteMapping("cartItem/{id}")
    public boolean DeleteCartItems(@PathVariable("id") UUID id) {
        return cartItemsService.deleteCartItems(id);
    }

    @GetMapping("cartItems/{cartId}")
    public List<CartItems> getCartItemsByCartsId(@PathVariable("cartId") UUID cartId) {
        return cartItemsService.readCartItemsByCartId(cartId);
    }

}
