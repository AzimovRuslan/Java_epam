package com.example.springtask.domain.store;

public class Price {
    private final String priceInByn;

    public Price(int priceInBynKopecks) {
        this.priceInByn = String.format("%d.%02d ", priceInBynKopecks / 100, priceInBynKopecks % 100);
    }

    public String getBynPrice() {
        return priceInByn + "BYN";
    }

    public String getUsdPrice() {
        double rate = 2.57;
        double bynPrice = Double.parseDouble(priceInByn);
        return String.format("%.02f USD", bynPrice / rate).replace(",", ".");
    }

    public String getEurPrice() {
        double rate = 2.92;
        double bynPrice = Double.parseDouble(priceInByn);
        return String.format("%.02f EUR", bynPrice / rate).replace(",", ".");
    }
}
