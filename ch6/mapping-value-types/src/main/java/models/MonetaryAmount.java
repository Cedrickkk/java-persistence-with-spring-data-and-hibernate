package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MonetaryAmount implements Serializable {
    private final BigDecimal value;
    private final Currency currency;

    public MonetaryAmount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public MonetaryAmount fromString(String str) {
        String[] split = str.split(" ");
        return new MonetaryAmount(
                new BigDecimal(split[0]),
                Currency.getInstance(split[1])
        );
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof MonetaryAmount that)) return false;

        return getValue().equals(that.getValue()) && getCurrency().equals(that.getCurrency());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getValue());
        result = 31 * result + Objects.hashCode(getCurrency());
        return result;
    }

    @Override
    public String toString() {
        return "MonetaryAmount{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }
}
