import epam.lab.SumCalculator;
import epam.lab.Cache;

import java.util.*;

public class Runner {
    public static void main(String[] args) {

        Cache lru = new Cache();



        for (SumCalculator sumCalculator : lru.getCache()) {
            System.out.println(sumCalculator.getResult());
        }
    }
}
