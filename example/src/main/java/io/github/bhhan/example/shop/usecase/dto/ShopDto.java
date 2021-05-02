package io.github.bhhan.example.shop.usecase.dto;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */
public class ShopDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ShopReq {
        private String name;
        private boolean open;
        private Money minOrderAmount;
        private double commissionRate;

        @Builder
        public ShopReq(String name, boolean open, Money minOrderAmount, double commissionRate) {
            this.name = name;
            this.open = open;
            this.minOrderAmount = minOrderAmount;
            this.commissionRate = commissionRate;
        }
    }
}
