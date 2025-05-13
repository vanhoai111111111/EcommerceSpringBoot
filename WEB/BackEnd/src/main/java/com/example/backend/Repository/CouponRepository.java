package com.example.backend.Repository;

import com.example.backend.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    boolean existsByCode(String code);
}
