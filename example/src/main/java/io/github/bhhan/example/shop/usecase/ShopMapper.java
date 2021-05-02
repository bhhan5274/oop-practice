package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.shop.domain.Shop;
import io.github.bhhan.example.shop.usecase.dto.ShopDto;
import org.springframework.stereotype.Component;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Component
public class ShopMapper {
    public Shop fromShopReq(ShopDto.ShopReq shopReq){
        return new Shop(shopReq.getName(), shopReq.isOpen(), shopReq.getMinOrderAmount(), shopReq.getCommissionRate());
    }
}
