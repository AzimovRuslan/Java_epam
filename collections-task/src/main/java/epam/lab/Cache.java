package epam.lab;
import java.util.*;

public class Cache {
    Deque<SumCalculator> cache = new ArrayDeque<>();

    private static final int MAX_SIZE = 3;

    public Deque<SumCalculator> getCache() {
        return cache;
    }

    public void lruCache(int firstNumber, int secondNumber) {
        SumCalculator calculator = new SumCalculator(firstNumber, secondNumber);
        calculator.calculation();

        int result = calculator.getResult();
        SumCalculator oldCalculator = null;

        if (cache.size() < MAX_SIZE) {
            if (!cache.isEmpty()) {
                for (SumCalculator sumCalculator : cache) {
                    if (sumCalculator.getResult() == result) {
                        oldCalculator = sumCalculator;
                    }
                }

                if (oldCalculator != null) {
                    cache.remove(oldCalculator);
                }
            }
            cache.addLast(calculator);
        } else {
            for (SumCalculator sumCalculator : cache) {
                if (sumCalculator.getResult() == result) {
                    oldCalculator = sumCalculator;
                }
            }

            if (oldCalculator != null) {
                cache.remove(oldCalculator);
            } else {
                cache.removeFirst();
            }
            cache.addLast(calculator);
        }
    }
}
