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
@Table(name = "ORDER_LINE_ITEM")
@Getter
@NoArgsConstructor
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LINE_ITEM_ID")
    private Long id;

    private Long menuId;
    private String name;
    private int count;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_LINE_ITEM_ID")
    private List<OrderOptionGroup> groups = new ArrayList<>();

    @Builder
    public OrderLineItem(Long id, Long menuId, String name, int count, List<OrderOptionGroup> groups){
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.groups.addAll(groups);
    }

    Money calculatePrice(){
        return Money.sum(groups, OrderOptionGroup::calculatePrice)
                .times((double) count);
    }

    public List<OptionGroup> convertToOptionGroups(){
        return groups.stream()
                .map(OrderOptionGroup::convertToOptionGroup)
                .collect(Collectors.toList());
    }
}
