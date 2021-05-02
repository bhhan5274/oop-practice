package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.money.Money;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */
class Fixture {
    static final String MENU1_NAME = "한우불고기버거";

    static final String MENU1_OPTION_GROUP1_NAME = "기본";
    static final boolean MENU1_OPTION_GROUP1_EXCLUSIVE = true;
    static final boolean MENU1_OPTION_GROUP1_BASIC = true;

    static final String MENU1_OPTION_GROUP1_SPEC1_NAME = "기본한우버거";
    static final Money MENU1_OPTION_GROUP1_SPEC1_PRICE = Money.wons(8000L);

    static final String MENU1_OPTION_GROUP2_NAME = "테코레이션";
    static final boolean MENU1_OPTION_GROUP2_EXCLUSIVE = false;
    static final boolean MENU1_OPTION_GROUP2_BASIC = false;

    static final String MENU1_OPTION_GROUP2_SPEC1_NAME = "빵가루";
    static final Money MENU1_OPTION_GROUP2_SPEC1_PRICE = Money.wons(2000L);
    static final String MENU1_OPTION_GROUP2_SPEC2_NAME = "식용금가루";
    static final Money MENU1_OPTION_GROUP2_SPEC2_PRICE = Money.wons(10000L);

    static final String MENU2_NAME = "새우버거";

    static final String MENU2_OPTION_GROUP1_NAME = "기본";
    static final boolean MENU2_OPTION_GROUP1_EXCLUSIVE = true;
    static final boolean MENU2_OPTION_GROUP1_BASIC = true;

    static final String MENU2_OPTION_GROUP1_SPEC1_NAME = "새우패티(소)";
    static final Money MENU2_OPTION_GROUP1_SPEC1_PRICE = Money.wons(3000L);
    static final String MENU2_OPTION_GROUP1_SPEC2_NAME = "새우패티(중)";
    static final Money MENU2_OPTION_GROUP1_SPEC2_PRICE = Money.wons(500L);
    static final String MENU2_OPTION_GROUP1_SPEC3_NAME = "새우패티(대)";
    static final Money MENU2_OPTION_GROUP1_SPEC3_PRICE = Money.wons(1000L);

    static Menu 한우불고기버거(){
        final Menu.MenuBuilder menuBuilder = makeMenuBuilder(MENU1_NAME);

        final OptionGroupSpecification optionGroupSpecification1 = makeOptionGroupSpecification(MENU1_OPTION_GROUP1_NAME, MENU1_OPTION_GROUP1_BASIC, MENU1_OPTION_GROUP1_EXCLUSIVE,
                Collections.singletonList(
                        makeOptionSpecification(MENU1_OPTION_GROUP1_SPEC1_NAME, MENU1_OPTION_GROUP1_SPEC1_PRICE)
                ));

        final OptionGroupSpecification optionGroupSpecification2 = makeOptionGroupSpecification(MENU1_OPTION_GROUP2_NAME, MENU1_OPTION_GROUP2_BASIC, MENU1_OPTION_GROUP2_EXCLUSIVE,
                Arrays.asList(
                        makeOptionSpecification(MENU1_OPTION_GROUP2_SPEC1_NAME, MENU1_OPTION_GROUP2_SPEC1_PRICE),
                        makeOptionSpecification(MENU1_OPTION_GROUP2_SPEC2_NAME, MENU1_OPTION_GROUP2_SPEC2_PRICE)
                ));

        return menuBuilder
                .basic(optionGroupSpecification1)
                .additives(Collections.singletonList(optionGroupSpecification2))
                .build();
    }

    static Menu 새우버거(){
        final Menu.MenuBuilder menuBuilder = makeMenuBuilder(MENU2_NAME);

        final OptionGroupSpecification optionGroupSpecification1 = makeOptionGroupSpecification(MENU2_OPTION_GROUP1_NAME, MENU2_OPTION_GROUP1_BASIC, MENU2_OPTION_GROUP1_EXCLUSIVE,
                Arrays.asList(
                        makeOptionSpecification(MENU2_OPTION_GROUP1_SPEC1_NAME, MENU2_OPTION_GROUP1_SPEC1_PRICE),
                        makeOptionSpecification(MENU2_OPTION_GROUP1_SPEC2_NAME, MENU2_OPTION_GROUP1_SPEC2_PRICE),
                        makeOptionSpecification(MENU2_OPTION_GROUP1_SPEC3_NAME, MENU2_OPTION_GROUP1_SPEC3_PRICE)
                ));

        return menuBuilder
                .basic(optionGroupSpecification1)
                .additives(Collections.singletonList(optionGroupSpecification1))
                .build();
    }

    private static Menu.MenuBuilder makeMenuBuilder(String name){
        return Menu.builder()
                .name(name);
    }

    private static OptionGroupSpecification makeOptionGroupSpecification(String name, boolean basic, boolean exclusive, List<OptionSpecification> options){
        return OptionGroupSpecification.builder()
                .name(name)
                .basic(basic)
                .exclusive(exclusive)
                .options(options)
                .build();
    }

    private static OptionSpecification makeOptionSpecification(String name, Money price){
        return OptionSpecification.builder()
                .name(name)
                .price(price)
                .build();
    }
}
