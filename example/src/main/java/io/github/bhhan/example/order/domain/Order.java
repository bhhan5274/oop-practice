package io.github.bhhan.example.order.domain;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@Getter
public class Order {
    public enum OrderStatus {
        ORDERED, PAYED, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    private Long userId;
    private Long shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Long id, Long userId, Long shopId, List<OrderLineItem> items,
                 LocalDateTime orderedTime, OrderStatus orderStatus){
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.orderLineItems.addAll(items);
    }

    public void place(OrderValidator orderValidator){
        orderValidator.validate(this);
        ordered();
        this.orderedTime = LocalDateTime.now();
    }

    public Money calculateTotalPrice(){
        return Money.sum(orderLineItems, OrderLineItem::calculatePrice);
    }

    public void payed(){
        this.orderStatus = OrderStatus.PAYED;
    }

    public void delivered(){
        this.orderStatus = OrderStatus.DELIVERED;
    }

    private void ordered(){
        this.orderStatus = OrderStatus.ORDERED;
    }
}
