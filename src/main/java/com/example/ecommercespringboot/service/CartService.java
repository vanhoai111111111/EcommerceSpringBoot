package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.AddProductInCartDto;
import com.example.ecommercespringboot.dto.OrderDto;
import com.example.ecommercespringboot.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId, String code);

    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPlaceOrders(Long userId);

    OrderDto searchOrderByTrackingId(UUID trackingId);

}
