package io.github.bhhan.example.common.domain.money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money money) {
        return money.longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long amount) {
        return Money.wons(amount);
    }
}
