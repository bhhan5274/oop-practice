package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.shop.Option;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static io.github.bhhan.example.shop.domain.Fixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */
class MenuTest {
    private Menu 한우불고기버거;
    private Menu 새우버거;

    @BeforeEach
    void setUp() {
        한우불고기버거 = 한우불고기버거();
        새우버거 = 새우버거();
    }

    @DisplayName("버거 기본금액체크")
    @Test
    void getBasePrice() {
        assertEquals(MENU1_OPTION_GROUP1_SPEC1_PRICE, 한우불고기버거.getBasePrice());
        assertEquals(MENU2_OPTION_GROUP1_SPEC1_PRICE, 새우버거.getBasePrice());
    }

    @DisplayName("메뉴 이름불일치")
    @Test
    void validateOrderFail1() {
        assertThrows(IllegalArgumentException.class, () -> 한우불고기버거.validateOrder("한우불고기버", null));
    }

    @DisplayName("옵션 그룹 이름불일치")
    @Test
    void validateOrderFail2() {
        final OptionGroup optionGroup = OptionGroup.builder()
                .name("버거")
                .build();

        assertThrows(IllegalArgumentException.class, () -> 한우불고기버거.validateOrder("한우불고기버거", Collections.singletonList(optionGroup)));
    }

    @DisplayName("옵션 그룹 스펙불일치")
    @Test
    void validateOrderFail3() {
        final OptionGroup optionGroup = OptionGroup.builder()
                .name(MENU1_OPTION_GROUP1_NAME)
                .options(Collections.singletonList(Option.builder()
                        .name("한우버거스페셜")
                        .price(MENU1_OPTION_GROUP1_SPEC1_PRICE)
                        .build()))
                .build();

        assertThrows(IllegalArgumentException.class, () -> 한우불고기버거.validateOrder("한우불고기버거", Collections.singletonList(optionGroup)));
    }

    @DisplayName("한우불고기 기본 성공")
    @Test
    void validateOrderSuccess1() {
        final OptionGroup optionGroup = OptionGroup.builder()
                .name(MENU1_OPTION_GROUP1_NAME)
                .options(Collections.singletonList(Option.builder()
                        .name(MENU1_OPTION_GROUP1_SPEC1_NAME)
                        .price(MENU1_OPTION_GROUP1_SPEC1_PRICE)
                        .build()))
                .build();

        한우불고기버거.validateOrder("한우불고기버거", Collections.singletonList(optionGroup));
    }

    @DisplayName("한우불고기 기본 데코 잣가루,금가루 추가 성공")
    @Test
    void validateOrderSuccess2() {
        final OptionGroup optionGroup1 = OptionGroup.builder()
                .name(MENU1_OPTION_GROUP1_NAME)
                .options(Collections.singletonList(Option.builder()
                        .name(MENU1_OPTION_GROUP1_SPEC1_NAME)
                        .price(MENU1_OPTION_GROUP1_SPEC1_PRICE)
                        .build()))
                .build();

        final OptionGroup optionGroup2 = OptionGroup.builder()
                .name(MENU1_OPTION_GROUP2_NAME)
                .options(Arrays.asList(
                        Option.builder()
                                .name(MENU1_OPTION_GROUP2_SPEC1_NAME)
                                .price(MENU1_OPTION_GROUP2_SPEC1_PRICE)
                                .build(),
                        Option.builder()
                                .name(MENU1_OPTION_GROUP2_SPEC2_NAME)
                                .price(MENU1_OPTION_GROUP2_SPEC2_PRICE)
                                .build()
                ))
                .build();

        한우불고기버거.validateOrder("한우불고기버거", Arrays.asList(optionGroup1, optionGroup2));
    }

    @DisplayName("한우불고기 기본 데코 금가루 추가 성공")
    @Test
    void validateOrderSuccess3() {
        final OptionGroup optionGroup1 = OptionGroup.builder()
                .name(MENU1_OPTION_GROUP1_NAME)
                .options(Collections.singletonList(Option.builder()
                        .name(MENU1_OPTION_GROUP1_SPEC1_NAME)
                        .price(MENU1_OPTION_GROUP1_SPEC1_PRICE)
                        .build()))
                .build();

        final OptionGroup optionGroup2 = OptionGroup.builder()
                .name(MENU1_OPTION_GROUP2_NAME)
                .options(Collections.singletonList(
                        Option.builder()
                                .name(MENU1_OPTION_GROUP2_SPEC2_NAME)
                                .price(MENU1_OPTION_GROUP2_SPEC2_PRICE)
                                .build()
                ))
                .build();

        한우불고기버거.validateOrder("한우불고기버거", Arrays.asList(optionGroup1, optionGroup2));
    }

    @DisplayName("새우버거 기본 성공")
    @Test
    void validateOrderSuccess4() {
        final OptionGroup optionGroup = OptionGroup.builder()
                .name(MENU2_OPTION_GROUP1_NAME)
                .options(Collections.singletonList(Option.builder()
                        .name(MENU2_OPTION_GROUP1_SPEC1_NAME)
                        .price(MENU2_OPTION_GROUP1_SPEC1_PRICE)
                        .build()))
                .build();

        새우버거.validateOrder("새우버거", Collections.singletonList(optionGroup));
    }

    @DisplayName("새우버거 기본 > (대)사이즈 변경 성공")
    @Test
    void validateOrderSuccess5() {
        final OptionGroup optionGroup = OptionGroup.builder()
                .name(MENU2_OPTION_GROUP1_NAME)
                .options(Collections.singletonList(Option.builder()
                        .name(MENU2_OPTION_GROUP1_SPEC3_NAME)
                        .price(MENU2_OPTION_GROUP1_SPEC3_PRICE)
                        .build()))
                .build();

        새우버거.validateOrder("새우버거", Collections.singletonList(optionGroup));
    }
}