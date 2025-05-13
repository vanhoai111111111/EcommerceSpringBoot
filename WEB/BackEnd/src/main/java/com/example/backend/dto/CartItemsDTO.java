package com.example.backend.dto;

import lombok.Data;

@Data
public class CartItemsDTO {
    private Long id;
    private Long price;
    private Long quantity;
    private Long productId;
    private Long userId;
    private Long orderId; // null nếu chưa đặt hàng
}
