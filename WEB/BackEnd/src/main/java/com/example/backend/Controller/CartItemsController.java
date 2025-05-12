package com.example.backend.Controller;



import com.example.backend.dto.CartItemsDTO;
import com.example.backend.Service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @PostMapping
    public CartItemsDTO addToCart(@RequestBody CartItemsDTO dto) {

        return cartItemsService.addToCart(dto);
    }

    @GetMapping("/user/{userId}")
    public List<CartItemsDTO> getCartByUser(@PathVariable Long userId) {
        return cartItemsService.getCartByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void removeCartItem(@PathVariable Long id) {
        cartItemsService.deleteCartItem(id);
    }
}
