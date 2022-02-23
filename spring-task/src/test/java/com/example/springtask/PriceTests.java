package com.example.springtask;

import com.example.springtask.domain.store.Price;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    void test() {
        Set<String> strings1 = new HashSet<>();
        strings1.add("a");
        strings1.add("b");
        strings1.add("c");

        Set<String > strings2 = new HashSet<>();
        strings2.add("a");
        strings2.add("b");
        strings2.add("c");

        Set<String> c = joinLists(strings1, strings2);

        for (String s : c) {
            System.out.println(s);
        }
    }

    public static <T> HashSet<T> joinLists(
            final Set<T> listA,
            final Set<T> listB) {

        boolean aEmpty = (listA == null) || listA.isEmpty();
        boolean bEmpty = (listB == null) || listB.isEmpty();
        //побитное И!
        if (aEmpty & bEmpty) {
            // оба пустые — отдаем новый пустой список
            return new HashSet<T>();
        } else if (aEmpty) {
            // один пустой — отдаем копию другого, содержащую все его элементы
            return new HashSet<T>(listB);
        } else if (bEmpty) {
            return new HashSet<T>(listA);
        } else {
            // оба непустые — объединяем
            HashSet<T> result = new HashSet<T>(
                    listA.size() + listB.size());
            result.addAll(listA);
            result.addAll(listB);
            return result;
        }
    }
}
