package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "OPTION_SPECS")
@NoArgsConstructor
@Getter
public class OptionSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_SPEC_ID")
    private Long id;

    private String name;
    private Money price;

    @Builder
    public OptionSpecification(Long id, String name, Money price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionSpecification that = (OptionSpecification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isSatisfiedBy(Option option){
        return Objects.equals(name, option.getName()) && Objects.equals(price, option.getPrice());
    }
}
