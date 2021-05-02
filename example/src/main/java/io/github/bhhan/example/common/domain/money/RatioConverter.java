package io.github.bhhan.example.common.domain.money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Converter(autoApply = true)
public class RatioConverter implements AttributeConverter<Ratio, Double> {
    @Override
    public Double convertToDatabaseColumn(Ratio ratio) {
        return ratio.getRate();
    }

    @Override
    public Ratio convertToEntityAttribute(Double rate) {
        return Ratio.valueOf(rate);
    }
}
