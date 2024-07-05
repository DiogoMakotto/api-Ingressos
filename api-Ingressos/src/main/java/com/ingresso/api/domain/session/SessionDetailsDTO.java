package com.ingresso.api.domain.session;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record SessionDetailsDTO(
        UUID id,
        String title,
        String description,
        Date date,
        String city,
        String uf,
        String imgUrl,
        String sessionUrl,
        List<CouponDTO> coupons) {

    public record CouponDTO(
            String code,
            Integer discount,
            Date validUntil){
    }
}
