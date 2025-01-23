package com.xplug.tech.utils.money;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Price {

    @Column
    private BigDecimal value;

    private String currencyCode;

    public Price() {
    }

    public Price(final BigDecimal value, final String currencyCode) {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public Price(double value, String currencyCode) {
        this.value = BigDecimal.valueOf(value);
        this.currencyCode = currencyCode;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String toString() {
        return "Price{value=" + this.value + ", currencyCode='" + this.currencyCode + '\'' + '}';
    }
}

