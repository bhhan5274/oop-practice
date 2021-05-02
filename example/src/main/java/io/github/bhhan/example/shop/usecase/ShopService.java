package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.event.EventPublisher;
import io.github.bhhan.example.shop.domain.Shop;
import io.github.bhhan.example.shop.domain.ShopRepository;
import io.github.bhhan.example.shop.usecase.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Transactional
@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public Long addShop(ShopDto.ShopReq shopReq){
        final Shop shop = shopMapper.fromShopReq(shopReq);
        return shopRepository.save(shop).getId();
    }

    public boolean isValidOrderAmount(Long shopId, Money amount){
        final Shop shop = findByShopId(shopId);
        return shop.isValidOrderAmount(amount);
    }

    public void shopOpen(Long shopId){
        final Shop shop = findByShopId(shopId);
        shop.open();
    }

    public void shopClose(Long shopId){
        final Shop shop = findByShopId(shopId);
        shop.close();
    }

    public Money calculateCommissionFee(Long shopId, Money price){
        return findByShopId(shopId).calculateCommissionFee(price);
    }

    public boolean isOpen(Long shopId){
        return findByShopId(shopId).isOpen();
    }

    private Shop findByShopId(Long shopId) {
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shopId"));
    }
}
