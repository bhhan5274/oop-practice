package io.github.bhhan.example.shop.usecase.dto;

import io.github.bhhan.example.common.domain.money.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */
public class MenuDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class MenuReq {
        private String name;
        private String description;
        private Long shopId;
        private OptionGroupSpecificationReq basic;
        private List<OptionGroupSpecificationReq> additives;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OptionGroupSpecificationReq {
        private String name;
        private boolean exclusive;
        private boolean basic;
        private List<OptionSpecificationReq> optionSpecs;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OptionSpecificationReq {
        private String name;
        private Money price;
    }
}
