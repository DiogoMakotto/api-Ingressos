package com.ingresso.api.controllers;

import com.ingresso.api.domain.coupon.Coupon;
import com.ingresso.api.domain.coupon.CouponRequestDTO;
import com.ingresso.api.domain.session.Session;
import com.ingresso.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("session/{sessionId}")
    public ResponseEntity<Coupon> addCouponToSession(@PathVariable UUID sessionId, @RequestBody CouponRequestDTO data){
        Coupon coupons = couponService.addCouponToSession(sessionId, data);
        return ResponseEntity.ok(coupons);
    }
}
