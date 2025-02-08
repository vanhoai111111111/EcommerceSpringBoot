package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.AnalyticsResponse;
import com.example.ecommercespringboot.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticsResponse calculateAnalytics();

}
