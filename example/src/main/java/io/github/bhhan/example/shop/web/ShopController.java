package io.github.bhhan.example.shop.web;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.shop.usecase.ShopService;
import io.github.bhhan.example.shop.usecase.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/shops")
public class ShopController {
    private final ShopService shopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long addShop(@RequestBody ShopDto.ShopReq shopReq){
        return shopService.addShop(shopReq);
    }

    @GetMapping("/{shopId}/status")
    @ResponseStatus(HttpStatus.OK)
    public boolean isOpen(@PathVariable Long shopId){
        return shopService.isOpen(shopId);
    }

    @PostMapping("/{shopId}/open")
    @ResponseStatus(HttpStatus.OK)
    public void open(@PathVariable Long shopId){
        shopService.shopOpen(shopId);
    }

    @PostMapping("/{shopId}/close")
    @ResponseStatus(HttpStatus.OK)
    public void close(@PathVariable Long shopId){
        shopService.shopClose(shopId);
    }

    @PostMapping("/{shopId}/validOrderAmount")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidOrderAmount(@PathVariable Long shopId, @RequestBody Money amount){
        return shopService.isValidOrderAmount(shopId, amount);
    }

    @PostMapping("/{shopId}/calculateCommissionFee")
    @ResponseStatus(HttpStatus.OK)
    public Money calculateCommissionFee(@PathVariable Long shopId, @RequestBody Money amount){
        return shopService.calculateCommissionFee(shopId, amount);
    }
}
