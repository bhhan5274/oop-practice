package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.money.Ratio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "SHOPS")
@Getter
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOP_ID")
    private Long id;

    private String name;
    private boolean open;

    private Money minOrderAmount;
    private Ratio commissionRate;

    public Shop(String name, boolean open, Money minOrderAmount, double commissionRate){
        this(null, name, open, minOrderAmount, Ratio.valueOf(commissionRate));
    }

    @Builder
    public Shop(Long id, String name, boolean open, Money minOrderAmount, Ratio commissionRate){
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
    }

    public boolean isValidOrderAmount(Money amount){
        return amount.isGreaterThanEqual(minOrderAmount);
    }

    public void open(){
        this.open = true;
    }

    public void close(){
        this.open = false;
    }

    public void modifyCommissionRate(Ratio commissionRate){
        this.commissionRate = commissionRate;
    }

    public Money calculateCommissionFee(Money price){
        return this.commissionRate.of(price);
    }
}
