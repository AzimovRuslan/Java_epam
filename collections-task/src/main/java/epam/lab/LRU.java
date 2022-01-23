package epam.lab;

import java.sql.Time;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.*;

public class LRU {
    List<SumCalculator> cache = new ArrayList<>();
    private static final int MAX_SIZE = 3;

    public List<SumCalculator> getCache() {
        return cache;
    }

    public List<SumCalculator> calculatorWithCache(int a, int b) {
        SumCalculator calculator = new SumCalculator(a, b);
        calculator.calculation();
        if (cache.size() <= MAX_SIZE) {
            cache.add(calculator);
        } else {
            Date oldDate = cache.get(0).getDate();
            for (int i = 0; i < cache.size(); i++) {
                if (cache.get(i).getDate().before(oldDate)) {
                    cache.remove(i);
                    cache.add(MAX_SIZE - 1, calculator);
                }
            }
        }
        return cache;
    }
}
