package com.example.backend.Repository;

import com.example.backend.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByUserIdAndOrderIsNull(Long userId); // cart chưa thanh toán
}

