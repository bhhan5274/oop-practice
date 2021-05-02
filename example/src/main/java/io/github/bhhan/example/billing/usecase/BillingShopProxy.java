package io.github.bhhan.example.billing.usecase;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.web.ShopController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Component
@RequiredArgsConstructor
public class BillingShopProxy {
    private final ShopController shopController;

    Money calculateCommissionFee(Long shopId, Money totalPrice) {
        return shopController.calculateCommissionFee(shopId, totalPrice);
    }
}
