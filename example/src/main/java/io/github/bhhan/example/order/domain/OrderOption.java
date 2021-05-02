package io.github.bhhan.example.order.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ORDER_OPTIONS")
@Getter
@NoArgsConstructor
public class OrderOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_ID")
    private Long id;

    private String name;
    private Money price;

    @Builder
    public OrderOption(Long id, String name, Money price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    Option convertToOption(){
        return Option.builder()
                .name(name)
                .price(price)
                .build();
    }
}
