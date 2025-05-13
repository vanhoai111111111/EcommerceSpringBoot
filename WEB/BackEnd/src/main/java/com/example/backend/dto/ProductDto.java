package com.example.backend.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private byte[] byteImg;
    private Long categoryId;
    private String categoryName;
}
