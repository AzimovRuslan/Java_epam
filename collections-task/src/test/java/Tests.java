import epam.lab.LFUCache;
import epam.lab.LRUCache;
import epam.lab.SumCalculator;
import org.testng.annotations.Test;

import java.util.*;

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
        Deque<Integer> result = new ArrayDeque<>();
        result.add(3);
        result.add(4);
        result.add(5);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 2).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 4).calculation());
        lru.lruCaching(new SumCalculator(0, 5).calculation());

        assertArrayEquals(result.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lruTest2() {
        Deque<Integer> result = new ArrayDeque<>();
        result.add(1);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 1).calculation());

        assertArrayEquals(result.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lruTest3() {
        Deque<Integer> result = new ArrayDeque<>();
        result.add(1);
        result.add(2);
        result.add(3);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 1).calculation());
        lru.lruCaching(new SumCalculator(0, 2).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());
        lru.lruCaching(new SumCalculator(0, 3).calculation());

        assertArrayEquals(result.toArray(), lru.getCache().toArray());
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
        Deque<Integer> result = new ArrayDeque<>();
        result.add(4);

        LRUCache<Integer> lru = new LRUCache<>();
        lru.lruCaching(new SumCalculator(0, 4).calculation());

        assertArrayEquals(result.toArray(), lru.getCache().toArray());
    }

    @Test
    public void lfuTest1() {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        result.put(1, 1);
        result.put(2, 1);
        result.put(3, 1);

        List<Integer> resultKeyList = new ArrayList<>(result.keySet());
        List<Integer> resultValueList = new ArrayList<>(result.values());

        LFUCache<Integer> cache = new LFUCache<>();

        SumCalculator sumCalculator1 = new SumCalculator(0, 1);
        SumCalculator sumCalculator2 = new SumCalculator(0, 2);
        SumCalculator sumCalculator3 = new SumCalculator(0, 3);

        cache.lfuCaching(sumCalculator1.calculation());
        cache.lfuCaching(sumCalculator2.calculation());
        cache.lfuCaching(sumCalculator3.calculation());

        List<Integer> keyList = new ArrayList<>(cache.getCache().keySet());
        List<Integer> valueList = new ArrayList<>(cache.getCache().values());

        assertArrayEquals(resultKeyList.toArray(), keyList.toArray());
        assertArrayEquals(resultValueList.toArray(), valueList.toArray());
    }

    @Test
    public void lfuTest2() {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        result.put(2, 4);
        result.put(5, 3);
        result.put(3, 1);

        List<Integer> resultKeyList = new ArrayList<>(result.keySet());
        List<Integer> resultValueList = new ArrayList<>(result.values());

        LFUCache<Integer> cache = new LFUCache<>();

        SumCalculator sumCalculator1 = new SumCalculator(0, 1);
        SumCalculator sumCalculator2 = new SumCalculator(0, 2);
        SumCalculator sumCalculator3 = new SumCalculator(0, 3);
        SumCalculator sumCalculator4 = new SumCalculator(0, 4);
        SumCalculator sumCalculator5 = new SumCalculator(0, 5);
        SumCalculator sumCalculator6 = new SumCalculator(0, 6);

        cache.lfuCaching(sumCalculator1.calculation());
        cache.lfuCaching(sumCalculator2.calculation());
        cache.lfuCaching(sumCalculator3.calculation());
        cache.lfuCaching(sumCalculator2.calculation());
        cache.lfuCaching(sumCalculator2.calculation());
        cache.lfuCaching(sumCalculator2.calculation());
        cache.lfuCaching(sumCalculator3.calculation());

        cache.lfuCaching(sumCalculator4.calculation());
        cache.lfuCaching(sumCalculator5.calculation());
        cache.lfuCaching(sumCalculator5.calculation());
        cache.lfuCaching(sumCalculator5.calculation());
        cache.lfuCaching(sumCalculator6.calculation());
        cache.lfuCaching(sumCalculator3.calculation());

        List<Integer> keyList = new ArrayList<>(cache.getCache().keySet());
        List<Integer> valueList = new ArrayList<>(cache.getCache().values());

        assertArrayEquals(resultKeyList.toArray(), keyList.toArray());
        assertArrayEquals(resultValueList.toArray(), valueList.toArray());
    }

    @Test
    public void lfuTest3() {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        result.put(-20, 1);
        result.put(5, 1);


        List<Integer> resultKeyList = new ArrayList<>(result.keySet());
        List<Integer> resultValueList = new ArrayList<>(result.values());

        LFUCache<Integer> cache = new LFUCache<>();

        SumCalculator sumCalculator1 = new SumCalculator(-40, 20);
        SumCalculator sumCalculator2 = new SumCalculator(3, 2);

        cache.lfuCaching(sumCalculator1.calculation());
        cache.lfuCaching(sumCalculator2.calculation());

        List<Integer> keyList = new ArrayList<>(cache.getCache().keySet());
        List<Integer> valueList = new ArrayList<>(cache.getCache().values());

        assertArrayEquals(resultKeyList.toArray(), keyList.toArray());
        assertArrayEquals(resultValueList.toArray(), valueList.toArray());
    }

    @Test
    public void lfuTest4() {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        result.put(1, 4);

        List<Integer> resultKeyList = new ArrayList<>(result.keySet());
        List<Integer> resultValueList = new ArrayList<>(result.values());

        LFUCache<Integer> cache = new LFUCache<>();

        SumCalculator sumCalculator1 = new SumCalculator(0, 1);
        SumCalculator sumCalculator2 = new SumCalculator(-3, 4);
        SumCalculator sumCalculator3 = new SumCalculator(-100, 101);
        SumCalculator sumCalculator4= new SumCalculator(0, 1);

        cache.lfuCaching(sumCalculator1.calculation());
        cache.lfuCaching(sumCalculator2.calculation());
        cache.lfuCaching(sumCalculator3.calculation());
        cache.lfuCaching(sumCalculator4.calculation());

        List<Integer> keyList = new ArrayList<>(cache.getCache().keySet());
        List<Integer> valueList = new ArrayList<>(cache.getCache().values());

        assertArrayEquals(resultKeyList.toArray(), keyList.toArray());
        assertArrayEquals(resultValueList.toArray(), valueList.toArray());
    }
}
