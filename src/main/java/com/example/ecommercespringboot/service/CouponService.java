package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.entity.Coupon;

import java.util.List;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();

}
