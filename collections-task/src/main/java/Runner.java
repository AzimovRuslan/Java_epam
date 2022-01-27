import epam.lab.LFUCache;
import epam.lab.LFUCacheN;
import epam.lab.SumCalculator;
import epam.lab.LRUCache;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
//        LRUCache<Integer> lru = new LRUCache<>();
//        lru.lruCaching(new SumCalculator(0, 1).getResult());
//        lru.lruCaching(new SumCalculator(0, 2).getResult());
        SumCalculator sumCalculator1 = new SumCalculator(0, 1);
        SumCalculator sumCalculator2 = new SumCalculator(0, 2);
        SumCalculator sumCalculator3 = new SumCalculator(0, 3);
        SumCalculator sumCalculator4 = new SumCalculator(0, 4);
        SumCalculator sumCalculator5 = new SumCalculator(0, 5);


        LFUCacheN<Integer> cacheN = new LFUCacheN<>();
        cacheN.lfuCaching(sumCalculator1.calculation(), sumCalculator1.getCounter());
        cacheN.lfuCaching(sumCalculator2.calculation(), sumCalculator2.getCounter());
        cacheN.lfuCaching(sumCalculator3.calculation(), sumCalculator3.getCounter());
        cacheN.lfuCaching(sumCalculator2.calculation(), sumCalculator2.getCounter());
        cacheN.lfuCaching(sumCalculator2.calculation(), sumCalculator2.getCounter());
        cacheN.lfuCaching(sumCalculator2.calculation(), sumCalculator2.getCounter());
        cacheN.lfuCaching(sumCalculator3.calculation(), sumCalculator3.getCounter());

//
        cacheN.lfuCaching(sumCalculator4.calculation(), sumCalculator4.getCounter());
        cacheN.lfuCaching(sumCalculator5.calculation(), sumCalculator5.getCounter());


        printMap(cacheN.getCache());
    }

    private static  <K, V>void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ";" + entry.getValue());
        }
    }
}


