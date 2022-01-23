import epam.lab.SumCalculator;
import epam.lab.LRU;

import java.util.*;
import java.text.*;

public class Runner {
    public static void main(String[] args) {

        LRU lru = new LRU();

        lru.calculatorWithCache(10, 0);
        lru.calculatorWithCache(20, 0);
        lru.calculatorWithCache(30, 0);

        for (SumCalculator sumCalculator : lru.getCache()) {
            System.out.println(sumCalculator);
        }

        System.out.println("-------------------------------------");

        lru.calculatorWithCache(40, 0);

        for (SumCalculator sumCalculator : lru.getCache()) {
            System.out.println(sumCalculator);
        }
    }
}
