package com.example.backend.dto;

import com.example.backend.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private Long id;
    private String orderDescription;
    private Date date;
    private BigDecimal amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private UUID trackingId;
    private Long userId;
    private Long couponId;
    private List<Long> cartItemIds;
}
