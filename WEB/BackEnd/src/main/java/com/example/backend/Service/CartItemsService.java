package com.example.backend.Service;



import com.example.backend.dto.CartItemsDTO;
import com.example.backend.entity.*;
import com.example.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemsService {

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public CartItemsDTO addToCart(CartItemsDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItems item = new CartItems();
        item.setProduct(product);
        item.setUser(user);
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());

        if (dto.getOrderId() != null) {
            Order order = orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            item.setOrder(order);
        }

        CartItems saved = cartItemsRepository.save(item);
        dto.setId(saved.getId());
        return dto;
    }

    public List<CartItemsDTO> getCartByUser(Long userId) {
        return cartItemsRepository.findByUserIdAndOrderIsNull(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteCartItem(Long id) {
        cartItemsRepository.deleteById(id);
    }

    private CartItemsDTO toDTO(CartItems item) {
        CartItemsDTO dto = new CartItemsDTO();
        dto.setId(item.getId());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setProductId(item.getProduct().getId());
        dto.setUserId(item.getUser().getId());
        if (item.getOrder() != null)
            dto.setOrderId(item.getOrder().getId());
        return dto;
    }
}

