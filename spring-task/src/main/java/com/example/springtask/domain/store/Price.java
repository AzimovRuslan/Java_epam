package com.example.springtask.domain.store;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private int conventionalUnit;

    private String currency;

    public Price() {
    }

    public Price(int conventionalUnit, String currency) {
        this.conventionalUnit = conventionalUnit;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getConventionalUnit() {
        return conventionalUnit;
    }

    public void setConventionalUnit(int conventionalUnit) {
        this.conventionalUnit = conventionalUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    //    private final String priceInByn;
//
//    public Price(int priceInBynKopecks) {
//        this.priceInByn = String.format("%d.%02d ", priceInBynKopecks / 100, priceInBynKopecks % 100);
//    }
//
//    public String getBynPrice() {
//        return priceInByn + "BYN";
//    }
//
//    public String getUsdPrice() {
//        double rate = 2.57;
//        double bynPrice = Double.parseDouble(priceInByn);
//        return String.format("%.02f USD", bynPrice / rate).replace(",", ".");
//    }
//
//    public String getEurPrice() {
//        double rate = 2.92;
//        double bynPrice = Double.parseDouble(priceInByn);
//        return String.format("%.02f EUR", bynPrice / rate).replace(",", ".");
//    }
}
