import epam.lab.LRUCache;
import epam.lab.SumCalculator;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    public void calculationTest1() {
        SumCalculator calculator = new SumCalculator(10, 15);
        calculator.calculation();

        assertEquals(25, calculator.getResult());
    }

    @Test
    public void calculationTest2() {
        SumCalculator calculator = new SumCalculator(-10, -3);
        calculator.calculation();

        assertEquals(-13, calculator.getResult());
    }

    @Test
    public void calculationTest3() {
        SumCalculator calculator = new SumCalculator(-20, 110);
        calculator.calculation();

        assertEquals(90, calculator.getResult());
    }

    @Test
    public void lruTest1() {
        Deque<Integer> testCache = new ArrayDeque<>();
        testCache.add(3);
        testCache.add(4);
        testCache.add(5);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 2).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 4).calculation());
        lru.lruCaching(new SumCalculator(0, 5).calculation());

        assertArrayEquals(testCache.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lruTest2() {
        Deque<Integer> testCache = new ArrayDeque<>();
        testCache.add(1);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 1).calculation());

        assertArrayEquals(testCache.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lruTest3() {
        Deque<Integer> testCache = new ArrayDeque<>();
        testCache.add(1);
        testCache.add(2);
        testCache.add(3);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 2).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());

        assertArrayEquals(testCache.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lruTest4() {
        Deque<Integer> testCache = new ArrayDeque<>();
        testCache.add(2);
        testCache.add(4);
        testCache.add(3);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 2).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 4).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());

        assertArrayEquals(testCache.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lruTest5() {
        Deque<Integer> testCache = new ArrayDeque<>();
        testCache.add(4);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 4).calculation());

        assertArrayEquals(testCache.toArray(), lru.getCache().toArray());
    }
}
