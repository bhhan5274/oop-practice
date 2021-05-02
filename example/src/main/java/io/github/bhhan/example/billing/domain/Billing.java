package io.github.bhhan.example.billing.domain;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "BILLINGS")
@NoArgsConstructor
@Getter
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BILLING_ID")
    private Long id;

    private Long shopId;
    private Money commission = Money.ZERO;

    public Billing(Long shopId){
        this(null, shopId, Money.ZERO);
    }

    @Builder
    public Billing(Long id, Long shopId, Money commission) {
        this.id = id;
        this.shopId = shopId;
        this.commission = commission;
    }

    public void billCommissionFee(Money commission){
        this.commission = this.commission.plus(commission);
    }
}
