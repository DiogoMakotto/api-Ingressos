package com.ingresso.api.service;

import com.ingresso.api.domain.coupon.Coupon;
import com.ingresso.api.domain.coupon.CouponRequestDTO;
import com.ingresso.api.domain.session.Session;
import com.ingresso.api.domain.session.SessionRequestDTO;
import com.ingresso.api.repositories.CouponRepository;
import com.ingresso.api.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Coupon addCouponToSession(UUID sessionId, CouponRequestDTO couponData){
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(new Date(couponData.valid()));
        coupon.setSession(session);

        return couponRepository.save(coupon);
    }
    public List<Coupon> consultCoupons(UUID sessionID, Date currentDate){
        return couponRepository.findBySessionIdAndValidAfter(sessionID, currentDate);
    }
}
