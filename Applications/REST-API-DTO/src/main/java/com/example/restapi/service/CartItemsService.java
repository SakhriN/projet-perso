package com.example.restapi.service;

import com.example.restapi.entity.CartItems;
import com.example.restapi.dto.CartItemsDTO;
import com.example.restapi.mapper.CartItemsMapper;
import com.example.restapi.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartItemsService {

    private final CartItemsRepository cartItemsRepository;
    private final CartItemsMapper cartItemsMapper;

    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository, CartItemsMapper cartItemsMapper) {
        this.cartItemsRepository = cartItemsRepository;
        this.cartItemsMapper = cartItemsMapper;
    }

    public CartItemsDTO addCartItems(CartItemsDTO cartItemsDTO) {
        CartItems cartItems = cartItemsMapper.toCartItems(cartItemsDTO);
        return cartItemsMapper.toCartItemsDto(cartItemsRepository.save(cartItems));
    }

    public Optional<CartItemsDTO> readOneCartItems(UUID id) {
        return cartItemsRepository.findById(id).map(cartItemsMapper::toCartItemsDto);
    }

    public List<CartItemsDTO> readCartItems() {
        return cartItemsRepository.findAll().stream()
                .map(cartItemsMapper::toCartItemsDto)
                .collect(Collectors.toList());
    }

    public boolean updateCartItems(CartItemsDTO cartItemsDTO) {
        boolean test = false;
        try {
            CartItems cartItems = cartItemsMapper.toCartItems(cartItemsDTO);
            cartItemsRepository.save(cartItems);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteCartItems(UUID id) {
        boolean test = false;
        try {
            cartItemsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public List<CartItemsDTO> ReadCartItemsByCartsId(UUID id) {
        return cartItemsRepository.findAllByCarts_CartsId(id).stream()
                .map(cartItemsMapper::toCartItemsDto)
                .collect(Collectors.toList());
    }
}
