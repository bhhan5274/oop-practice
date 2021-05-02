package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "MENUS")
@Getter
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "FOOD_NAME")
    private String name;

    @Column(name = "FOOD_DESCRIPTION")
    private String description;

    private Long shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList<>();

    @Builder
    public Menu(Long id, String name, String description, Long shopId,
                OptionGroupSpecification basic,
                List<OptionGroupSpecification> additives){
        this.id = id;
        this.name = name;
        this.description = description;
        this.shopId = shopId;
        this.optionGroupSpecs.add(basic);

        if(Objects.nonNull(additives)){
            this.optionGroupSpecs.addAll(additives);
        }
    }

    public void validateOrder(String menuName, List<OptionGroup> optionGroups){
        if(!this.name.equals(menuName)){
            throw new IllegalArgumentException("기본 상품이 변경됐습니다.");
        }

        if(!isSatisfiedBy(optionGroups)){
            throw new IllegalArgumentException("메뉴가 변경됐습니다.");
        }
    }

    public Money getBasePrice(){
        return getBasicOptionGroupSpecs()
                .getOptionSpecs()
                .get(0)
                .getPrice();
    }

    private OptionGroupSpecification getBasicOptionGroupSpecs() {
        return this.optionGroupSpecs
                .stream()
                .filter(OptionGroupSpecification::isBasic)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean isSatisfiedBy(List<OptionGroup> cartOptionGroups){
        return cartOptionGroups
                .stream()
                .allMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(OptionGroup group) {
        return this.optionGroupSpecs
                .stream()
                .anyMatch(spec -> spec.isSatisfiedBy(group));
    }
}
