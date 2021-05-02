package io.github.bhhan.example.order.domain.event;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.event.DomainEvent;
import io.github.bhhan.example.order.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Getter
@NoArgsConstructor
public class OrderDeliveredEvent implements DomainEvent {
    private Long orderId;
    private Long shopId;
    private Money totalPrice;

    public OrderDeliveredEvent(Order order) {
        this.orderId = order.getId();
        this.shopId = order.getShopId();
        this.totalPrice = order.calculateTotalPrice();
    }
}
