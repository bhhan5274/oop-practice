package io.github.bhhan.example.order.domain.event;

import io.github.bhhan.example.common.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Getter
@NoArgsConstructor
public class OrderPayedEvent implements DomainEvent {
    private Long orderId;

    public OrderPayedEvent(Long orderId) {
        this.orderId = orderId;
    }
}
