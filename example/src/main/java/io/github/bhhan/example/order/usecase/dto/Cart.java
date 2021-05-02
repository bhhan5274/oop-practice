package io.github.bhhan.example.order.usecase.dto;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private Long shopId;
    private Long userId;
    private List<CartLineItem> cartLineItems = new ArrayList<>();

    @Builder
    public Cart(Long shopId, Long userId, CartLineItem... cartLineItems){
        this.shopId = shopId;
        this.userId = userId;
        this.cartLineItems = Arrays.asList(cartLineItems);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartLineItem {
        private Long menuId;
        private String name;
        private int count;
        private List<CartOptionGroup> groups = new ArrayList<>();

        @Builder
        public CartLineItem(Long menuId, String name, int count, CartOptionGroup... groups) {
            this.menuId = menuId;
            this.name = name;
            this.count = count;
            this.groups = Arrays.asList(groups);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartOptionGroup {
        private String name;
        private List<CartOption> options = new ArrayList<>();

        @Builder
        public CartOptionGroup(String name, CartOption... options) {
            this.name = name;
            this.options = Arrays.asList(options);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartOption {
        private String name;
        private Money price;

        @Builder
        public CartOption(String name, Money price) {
            this.name = name;
            this.price = price;
        }
    }
}
