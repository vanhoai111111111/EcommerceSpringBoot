package com.example.backend.dto;

import lombok.Data;

@Data
public class WishListDTO {
    private Long id;
    private Long productId;
    private Long userId;
}
