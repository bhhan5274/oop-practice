package io.github.bhhan.example.common.domain.money;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */
public class Ratio {
    private Double rate;

    public static Ratio valueOf(Double rate){
        return new Ratio(rate);
    }

    private Ratio(Double rate){
        this.rate = rate;
    }

    public Money of(Money price){
        return price.times(rate);
    }

    public Double getRate(){
        return this.rate;
    }
}
