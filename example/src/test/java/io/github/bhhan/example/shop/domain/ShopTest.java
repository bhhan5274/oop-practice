package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.money.Ratio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */
class ShopTest {
    private Shop shop;

    @BeforeEach
    void setUp(){
        shop = Shop.builder()
                .name("롯데리아")
                .minOrderAmount(Money.wons(13000L))
                .commissionRate(Ratio.valueOf(0.2))
                .open(true)
                .build();
    }

    @DisplayName("최소주문금액 테스트")
    @Test
    void isValidOrderAmount(){
        assertTrue(shop.isValidOrderAmount(Money.wons(13000L)));
        assertTrue(shop.isValidOrderAmount(Money.wons(16000L)));
        assertFalse(shop.isValidOrderAmount(Money.wons(12900L)));
        assertFalse(shop.isValidOrderAmount(Money.wons(12999L)));
    }

    @DisplayName("수수료 계산")
    @Test
    void test(){
        assertEquals(Money.wons(4000L), shop.calculateCommissionFee(Money.wons(20000L)));
    }
}