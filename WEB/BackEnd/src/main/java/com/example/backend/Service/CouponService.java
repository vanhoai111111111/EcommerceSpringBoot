package com.example.backend.Service;

import com.example.backend.Repository.CouponRepository;
import com.example.backend.dto.CouponDTO;
import com.example.backend.entity.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public CouponDTO createCoupon(CouponDTO dto) {
        if (couponRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Mã giảm giá đã tồn tại!");
        }
        Coupon coupon = convertToEntity(dto);
        return convertToDTO(couponRepository.save(coupon));
    }

    public List<CouponDTO> getAllCoupons() {
        return couponRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CouponDTO> getCouponById(Long id) {
        return couponRepository.findById(id).map(this::convertToDTO);
    }

    public CouponDTO updateCoupon(Long id, CouponDTO dto) {
        Coupon existing = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mã giảm giá"));

        existing.setName(dto.getName());
        existing.setCode(dto.getCode());
        existing.setDiscount(dto.getDiscount());
        existing.setExpirationDate(dto.getExpirationDate());

        return convertToDTO(couponRepository.save(existing));
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    private Coupon convertToEntity(CouponDTO dto) {
        Coupon coupon = new Coupon();
        coupon.setName(dto.getName());
        coupon.setCode(dto.getCode());
        coupon.setDiscount(dto.getDiscount());
        coupon.setExpirationDate(dto.getExpirationDate());
        return coupon;
    }

    private CouponDTO convertToDTO(Coupon coupon) {
        CouponDTO dto = new CouponDTO();
        dto.setName(coupon.getName());
        dto.setCode(coupon.getCode());
        dto.setDiscount(coupon.getDiscount());
        dto.setExpirationDate(coupon.getExpirationDate());
        return dto;
    }
}

