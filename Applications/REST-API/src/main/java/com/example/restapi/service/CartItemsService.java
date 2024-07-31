package com.example.restapi.service;

import com.example.restapi.entity.CartItems;
import com.example.restapi.entity.OrderItems;
import com.example.restapi.repository.CartItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemsService {

    private final CartItemsRepository cartItemsRepository;


    public CartItemsService(CartItemsRepository cartItemsRepository) {
        this.cartItemsRepository = cartItemsRepository;
    }


    public CartItems addCartItems(CartItems cartItems) {
        return cartItemsRepository.save(cartItems);
    }

    public Optional<CartItems> readOneCartItem(UUID id) {
        return cartItemsRepository.findById(id);
    }

    public List<CartItems> readCartItems() {
        return cartItemsRepository.findAll();
    }

    public boolean updateCartItems(CartItems cartItems) {
        boolean test = false;
        try {
            cartItemsRepository.save(cartItems);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteCartItems(UUID id) {
        boolean test = false;
        try {
            cartItemsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

    public List<CartItems> readCartItemsByCartId(UUID id){
        return cartItemsRepository.findAllByCarts_CartsId(id);
    }

}
