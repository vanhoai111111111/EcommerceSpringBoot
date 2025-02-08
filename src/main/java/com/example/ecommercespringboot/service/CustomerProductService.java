package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.ProductDetailDto;
import com.example.ecommercespringboot.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProducts();

    List<ProductDto> searchProductByTitle(String title);

    ProductDetailDto getProductDetailById(Long productId);

}
