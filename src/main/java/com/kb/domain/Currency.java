package com.kb.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Currency.
 */
@Entity
@Table(name = "CURRENCY")
public class Currency extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "symbol", unique = true)
    private String symbol;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "rate", precision=10, scale=2)
    private BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Currency currency = (Currency) o;

        if ( ! Objects.equals(id, currency.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", symbol='" + symbol + "'" +
                ", currency_name='" + currencyName + "'" +
                ", available='" + available + "'" +
                ", rate='" + rate + "'" +
                '}';
    }
}
