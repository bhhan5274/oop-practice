package io.github.bhhan.example.order.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@Getter
@NoArgsConstructor
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_GROUP_ID")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_OPTION_GROUP_ID")
    private List<OrderOption> orderOptions = new ArrayList<>();

    @Builder
    public OrderOptionGroup(Long id, String name, List<OrderOption> orderOptions){
        this.id = id;
        this.name = name;
        this.orderOptions.addAll(orderOptions);
    }

    Money calculatePrice(){
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

    OptionGroup convertToOptionGroup(){
        return OptionGroup.builder()
                .name(name)
                .options(orderOptions.stream()
                .map(OrderOption::convertToOption)
                .collect(Collectors.toList()))
                .build();
    }
}
