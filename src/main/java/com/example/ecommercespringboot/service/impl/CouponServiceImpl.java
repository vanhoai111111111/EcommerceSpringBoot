package com.example.ecommercespringboot.service.impl;

import com.example.ecommercespringboot.entity.Coupon;
import com.example.ecommercespringboot.exception.ValidationException;
import com.example.ecommercespringboot.repository.CouponRepository;
import com.example.ecommercespringboot.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon) {
        if (couponRepository.existsByCode(coupon.getCode())) {
            throw new ValidationException("Coupon code already exists.");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

}
