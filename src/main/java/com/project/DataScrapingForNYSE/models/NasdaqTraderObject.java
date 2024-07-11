package com.project.DataScrapingForNYSE.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "nasdaq_trader_object")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

}
