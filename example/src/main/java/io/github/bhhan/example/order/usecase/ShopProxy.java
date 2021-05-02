package io.github.bhhan.example.order.usecase;

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
public class ShopProxy {
    private final ShopController shopController;

    boolean isOpen(Long shopId){
        return shopController.isOpen(shopId);
    }

    boolean isValidOrderAmount(Long shopId, Money amount){
        return shopController.isValidOrderAmount(shopId, amount);
    }
}
