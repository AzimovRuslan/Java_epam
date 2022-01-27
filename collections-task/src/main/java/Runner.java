import epam.lab.LFUCache;
import epam.lab.LFUCacheN;
import epam.lab.SumCalculator;
import epam.lab.LRUCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
//        LFUCache lfuCache = new LFUCache(3);
//        lfuCache.addCacheEntry(1, "Ruslan");
//        lfuCache.addCacheEntry(2, "Ruslan");
//        lfuCache.addCacheEntry(3, "Ruslan");
//        lfuCache.addCacheEntry(4, "Ruslan");
//        lfuCache.addCacheEntry(4, "qwe");
//        lfuCache.addCacheEntry(4, "eqw");
//
//        printMap(lfuCache.getCacheMap());

//        Map<Integer, String> map = new LinkedHashMap<>();
//
//        map.put(1, "fewfwefw");
//        map.put(4, "dvrgre");
//        map.put(3, "fewvvfdvfdfwefw");
//        map.put(4, "eee");
//        printMap(map);


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


