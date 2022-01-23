import epam.lab.SumCalculator;
import epam.lab.Cache;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Cache<Integer> lru = new Cache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 2).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 4).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());

//        for (Integer integer : lru.getCalculators()) {
//            System.out.println(integer);
//        }

    }
}
