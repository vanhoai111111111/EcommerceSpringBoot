package com.example.backend.Service;

import com.example.backend.dto.OrderDTO;
import com.example.backend.entity.*;
import com.example.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order();
        order.setOrderDescription(dto.getOrderDescription());
        order.setDate(new Date());
        order.setAmount(dto.getAmount());
        order.setAddress(dto.getAddress());
        order.setPayment(dto.getPayment());
        order.setOrderStatus(dto.getOrderStatus());
        order.setTotalAmount(dto.getTotalAmount());
        order.setDiscount(dto.getDiscount());
        order.setTrackingId(UUID.randomUUID());

        userRepository.findById(dto.getUserId()).ifPresent(order::setUser);
        if (dto.getCouponId() != null) {
            couponRepository.findById(dto.getCouponId()).ifPresent(order::setCoupon);
        }

        Order savedOrder = orderRepository.save(order);

        if (dto.getCartItemIds() != null) {
            List<CartItems> cartItems = cartItemsRepository.findAllById(dto.getCartItemIds());
            cartItems.forEach(item -> item.setOrder(savedOrder));
            cartItemsRepository.saveAll(cartItems);
        }

        return toDTO(savedOrder);
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public OrderDTO getOrderByTrackingId(UUID trackingId) {
        return orderRepository.findByTrackingId(trackingId).map(this::toDTO).orElse(null);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDescription(order.getOrderDescription());
        dto.setDate(order.getDate());
        dto.setAmount(order.getAmount());
        dto.setAddress(order.getAddress());
        dto.setPayment(order.getPayment());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDiscount(order.getDiscount());
        dto.setTrackingId(order.getTrackingId());
        if (order.getUser() != null) dto.setUserId(order.getUser().getId());
        if (order.getCoupon() != null) dto.setCouponId(order.getCoupon().getId());
        if (order.getCartItems() != null) {
            dto.setCartItemIds(order.getCartItems().stream().map(CartItems::getId).collect(Collectors.toList()));
        }
        return dto;
    }
}
