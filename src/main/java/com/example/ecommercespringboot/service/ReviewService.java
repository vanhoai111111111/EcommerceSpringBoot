package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.OrderedProductsResponseDto;
import com.example.ecommercespringboot.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
