package com.project.DataScrapingForNYSE.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "nasdaq_trader_objects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NasdaqTraderObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nasdaq_trader_object_id")
    private int id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "nasdaq_action")
    private String nasdaqAction;
    @Column(name = "bx_action")
    private String bxAction;
    @Column(name = "psx_action")
    private String psxAction;
    @Column(name = "effective_date")
    private String effectiveDate;
    @Column(name = "primary_listing_market")
    private char primaryListingMarket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NasdaqTraderObject that = (NasdaqTraderObject) o;
        return primaryListingMarket == that.primaryListingMarket &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(nasdaqAction, that.nasdaqAction) &&
                Objects.equals(bxAction, that.bxAction) &&
                Objects.equals(psxAction, that.psxAction) &&
                Objects.equals(effectiveDate, that.effectiveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName, nasdaqAction, bxAction, psxAction, effectiveDate, primaryListingMarket);
    }
}
