package com.example.springtask;

import com.example.springtask.domain.store.Price;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriceTests {
    @Test
    void priceTest1() {
        Price price = new Price(130);

        assertEquals("1.30 BYN", price.getBynPrice());
        assertEquals("0.51 USD", price.getUsdPrice());
        assertEquals("0.45 EUR", price.getEurPrice());
    }

    @Test
    void priceTest2() {
        Price price = new Price(4000);

        assertEquals("40.00 BYN", price.getBynPrice());
        assertEquals("15.56 USD", price.getUsdPrice());
        assertEquals("13.70 EUR", price.getEurPrice());
    }
}
